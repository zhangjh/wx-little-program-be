package me.zhangjh.wx.program.order;

import lombok.extern.slf4j.Slf4j;
import me.zhangjh.share.response.Response;
import me.zhangjh.wx.program.model.order.TblCoupon;
import me.zhangjh.wx.program.service.order.TblCouponService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 1:59 PM 2023/3/8
 * @Description
 */
@RestController
@RequestMapping("/wx/coupon")
@CrossOrigin
@Slf4j
public class CouponController {

    @Autowired
    private TblCouponService tblCouponService;

    @GetMapping("/generate")
    public List<String> generate(String couponType, Integer amount, Integer discount) {
        return tblCouponService.generateCoupons(couponType, amount, discount);
    }

    @GetMapping("/query")
    public Response<List<TblCoupon>> query(String secret) {
        Assert.isTrue(StringUtils.isNotEmpty(secret) &&
                "wired_sheep".equals(secret), "secret错误");
        TblCoupon query = new TblCoupon();
        List<TblCoupon> coupons = tblCouponService.query(query);
        return Response.success(coupons);
    }
}
