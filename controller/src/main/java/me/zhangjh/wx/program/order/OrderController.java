package me.zhangjh.wx.program.order;

import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.cipher.RSASigner;
import com.wechat.pay.java.core.util.NonceUtil;
import com.wechat.pay.java.core.util.PemUtil;
import com.wechat.pay.java.service.payments.jsapi.JsapiService;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayResponse;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.share.response.PageResponse;
import me.zhangjh.share.response.Response;
import me.zhangjh.wx.program.constant.CouponEnum;
import me.zhangjh.wx.program.constant.PayStatusEnum;
import me.zhangjh.wx.program.model.*;
import me.zhangjh.wx.program.model.order.TblCoupon;
import me.zhangjh.wx.program.model.order.TblOrder;
import me.zhangjh.wx.program.model.order.TblOrderItem;
import me.zhangjh.wx.program.order.request.OrderRequest;
import me.zhangjh.wx.program.order.request.WxOrderRequest;
import me.zhangjh.wx.program.order.response.OrderVO;
import me.zhangjh.wx.program.service.order.TblCouponService;
import me.zhangjh.wx.program.service.order.TblOrderItemService;
import me.zhangjh.wx.program.service.order.TblOrderService;
import me.zhangjh.wx.program.util.Authorization;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author zhangjh451@midea.com
 * @date 12:26 AM 2023/3/7
 * @Description
 */
@RestController
@RequestMapping("/wx")
@CrossOrigin
@Slf4j
public class OrderController {

    @Value("${wx.mchId}")
    private String mchId;

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.privateKey.path}")
    private String wxPrivateKeyPath;

    @Value("${wx.privateKey.serino}")
    private String wxPrivateKeySerino;

    @Value("${wx.apiV3.key}")
    private String apiV3Key;

    private Config config;

    @PostConstruct
    public void init() {
        this.config = new RSAAutoCertificateConfig.Builder()
                .merchantId(mchId)
                .privateKeyFromPath(wxPrivateKeyPath)
                .merchantSerialNumber(wxPrivateKeySerino)
                .apiV3Key(apiV3Key)
                .build();
    }

    @Autowired
    private TblOrderItemService tblOrderItemService;

    @Autowired
    private TblOrderService tblOrderService;

    @Autowired
    private TblCouponService tblCouponService;

    @GetMapping("/getOrderItems")
    public Response<List<TblOrderItem>> getOrderItems(String product) {
        TblOrderItem orderItem = new TblOrderItem();
        if(StringUtils.isNotEmpty(product)) {
            product = product.toUpperCase();
        }
        orderItem.setProduct(product);
        List<TblOrderItem> orderItems = tblOrderItemService.queryOrderItems(orderItem);
        return Response.success(orderItems);
    }

    @PostMapping("/createOrder")
    public Response<String> createOrder(@RequestBody OrderRequest orderRequest, HttpServletRequest req) {
        String userId = req.getHeader("userId");
        Assert.isTrue(StringUtils.isNotEmpty(userId), "userId为空");
        String code = orderRequest.getCode();
        Integer price = orderRequest.getPrice();
        String couponCode = orderRequest.getCouponCode();
        String desc = orderRequest.getDesc();
        Assert.isTrue(StringUtils.isNotEmpty(code), "产品code为空");
        Assert.isTrue(StringUtils.isNotEmpty(desc), "产品描述为空");
        Assert.isTrue(price != null, "产品价格为空");

        if(StringUtils.isNotEmpty(couponCode)) {
            TblCoupon tblCoupon = tblCouponService.queryByCode(couponCode);
            if(tblCoupon == null) {
                return Response.fail("无效的优惠券");
            }
            // 计算优惠券的折扣，扩大了100倍
            int discount = 0;
            String couponType = tblCoupon.getType();
            if(CouponEnum.DISCOUNT.name().equals(couponType)) {
                discount = tblCoupon.getDiscount();
            }
            Assert.isTrue(discount <= 100, "折扣设置错误");
            price = price * discount / 100;
        }

        // 调用微信api生成订单
        WxOrderRequest wxOrderRequest = new WxOrderRequest();
        wxOrderRequest.setUserId(userId);
        wxOrderRequest.setAppid(appId);
        wxOrderRequest.setMchid(mchId);
        wxOrderRequest.setPrice(price);
        wxOrderRequest.setDescription(desc);
        wxOrderRequest.setOutTradeNo(UUID.randomUUID().toString().replace("-", ""));
        wxOrderRequest.setNotifyUrl("https://wx.zhangjh.me/payCb");

        // 如果用了免费券，则不再调用微信创建订单
        String orderId = wxOrderRequest.getOutTradeNo();
        if(!price.equals(0)) {
            orderId = wxCreateOrder(wxOrderRequest);
        }
        try {
            TblOrder tblOrder = new TblOrder();
            tblOrder.setUserId(userId);
            tblOrder.setOrderId(orderId);
            tblOrder.setPayStatus(PayStatusEnum.UNPAID.getStatus());
            tblOrder.setItemCode(code);
            tblOrder.setPrice(price);
            tblOrder.setCouponCode(couponCode);
            tblOrderService.insert(tblOrder);
        } catch (Throwable t) {
            // 记录日志，不阻断
            log.error("insert order exception, t:", t);
        }
        return Response.success(orderId);
    }

    @GetMapping("/getPayParam")
    public Response<Authorization> getPayParam(String wxOrderId) {
        Assert.isTrue(StringUtils.isNotEmpty(wxOrderId), "订单id为空");
        Authorization authorization = new Authorization();
        long timeStamp = System.currentTimeMillis();
        authorization.setTimestamp(String.valueOf(timeStamp));
        String nonce = NonceUtil.createNonce(32);
        authorization.setNonce(nonce);
        authorization.setPack(wxOrderId);
        // 使用appId、timeStamp、nonce、package计算签名
        authorization.setSignature(buildPaySign(appId, timeStamp, nonce, "prepay_id=" + wxOrderId));
        return Response.success(authorization);
    }

    @GetMapping("/cancelOrder")
    public Response<Void> cancelOrder(String wxOrderId) {
        Assert.isTrue(StringUtils.isNotEmpty(wxOrderId), "wxOrderId为空");
        TblOrder tblOrder = new TblOrder();
        tblOrder.setOrderId(wxOrderId);
        tblOrder.setPayStatus(PayStatusEnum.CANCEL.getStatus());
        tblOrderService.update(tblOrder);
        return Response.success(null);
    }

    @GetMapping("/completeOrder")
    @Transactional(rollbackFor = Exception.class)
    public Response<Void> completeOrder(String wxOrderId) {
        Assert.isTrue(StringUtils.isNotEmpty(wxOrderId), "wxOrderId为空");
        TblOrder tblOrder = new TblOrder();
        tblOrder.setOrderId(wxOrderId);
        tblOrder.setPayStatus(PayStatusEnum.PAYED.getStatus());
        tblOrderService.update(tblOrder);

        TblOrder order = tblOrderService.query(wxOrderId);
        // 如果使用了优惠券，将优惠券置为已用
        String couponCode = order.getCouponCode();
        if(StringUtils.isNotEmpty(couponCode)) {
            tblCouponService.setCouponUsed(couponCode);
        }
        return Response.success(null);
    }

    /**
     * 只查询用户有效订单
     */
    @GetMapping("/queryOrdersByUser")
    public PageResponse<OrderVO> queryOrdersByUser(String userId, Integer pageIndex) {
        Assert.isTrue(StringUtils.isNotEmpty(userId), "userId为空");
        PageOrderQuery pageOrderQuery = new PageOrderQuery();
        pageOrderQuery.setUserId(userId);
        pageOrderQuery.setPayStatus(PayStatusEnum.PAYED.getStatus());
//        pageOrderQuery.setPayStatusList(Arrays.asList(PayStatusEnum.UNPAID.getStatus(),
//                PayStatusEnum.PAYED.getStatus()));
        pageOrderQuery.setPageIndex(pageIndex);
        pageOrderQuery.setPageSize(5);
        List<TblOrder> orderList = tblOrderService.pageQuery(pageOrderQuery);

        int total = tblOrderService.count(pageOrderQuery);

        List<OrderVO> orderVOS = new ArrayList<>();
        for (TblOrder tblOrder : orderList) {
            OrderVO orderVO = new OrderVO();
            orderVO.setId(tblOrder.getId());
            orderVO.setOrderId(tblOrder.getOrderId());
            orderVO.setCreateTime(tblOrder.getCreateTime());
            orderVO.setItemCode(tblOrder.getItemCode());
            orderVO.setPayStatus(PayStatusEnum.getDescByStatus(tblOrder.getPayStatus()));
            orderVO.setPrice(tblOrder.getPrice());
            TblOrderItem orderItemQuery = new TblOrderItem();
            orderItemQuery.setCode(tblOrder.getItemCode());
            List<TblOrderItem> tblOrderItems = tblOrderItemService.queryOrderItems(orderItemQuery);
            TblOrderItem tblOrderItem = tblOrderItems.get(0);
            orderVO.setItemDesc(tblOrderItem.getDesc());
            orderVO.setTotal(tblOrderItem.getAmount());
            orderVOS.add(orderVO);
        }
        PageResponse<OrderVO> response = PageResponse.success(orderVOS);
        response.setTotal(total);
        return response;
    }

    private String wxCreateOrder(WxOrderRequest wxOrderRequest) {
        JsapiService service = new JsapiService.Builder().config(config).build();
        // request.setXxx(val)设置所需参数，具体参数可见Request定义
        PrepayRequest request = new PrepayRequest();
        Amount amount = new Amount();
        amount.setTotal(wxOrderRequest.getPrice());
        request.setAmount(amount);
        request.setAppid(appId);
        request.setMchid(mchId);
        request.setDescription(wxOrderRequest.getDescription());
        request.setNotifyUrl(wxOrderRequest.getNotifyUrl());
        request.setOutTradeNo(UUID.randomUUID().toString().replace("-", ""));
        Payer payer = new Payer();
        payer.setOpenid(wxOrderRequest.getUserId());
        request.setPayer(payer);
        PrepayResponse response = service.prepay(request);
        return response.getPrepayId();
    }

    /**
     * 客户端调起微信支付时的签名计算
     */
    private String buildPaySign(String appId, long timestamp, String echostr, String body) {
        String sb = appId + "\n" +
                timestamp + "\n" +
                echostr + "\n" +
                body + "\n";
        RSASigner signer = new RSASigner(mchId, PemUtil.loadPrivateKeyFromPath(wxPrivateKeyPath));
        return signer.sign(sb).getSign();
    }
}
