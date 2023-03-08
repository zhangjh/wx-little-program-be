package me.zhangjh.wx.program.order;

import lombok.extern.slf4j.Slf4j;
import me.zhangjh.share.response.Response;
import me.zhangjh.wx.program.constant.OrderItemEnum;
import me.zhangjh.wx.program.constant.PayStatusEnum;
import me.zhangjh.wx.program.model.*;
import me.zhangjh.wx.program.model.chatgpt.TblChat;
import me.zhangjh.wx.program.model.chatgpt.TblDraw;
import me.zhangjh.wx.program.model.order.TblOrder;
import me.zhangjh.wx.program.model.order.TblOrderItem;
import me.zhangjh.wx.program.service.order.TblOrderItemService;
import me.zhangjh.wx.program.service.order.TblOrderService;
import me.zhangjh.wx.program.service.chatgpt.TblChatService;
import me.zhangjh.wx.program.service.chatgpt.TblDrawService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangjh451@midea.com
 * @date 12:03 AM 2023/3/7
 * @Description
 */
@RestController
@RequestMapping("/wx/chatHelper")
@CrossOrigin
@Slf4j
public class UsageCheckController {

    @Autowired
    private TblChatService tblChatService;

    @Autowired
    private TblDrawService tblDrawService;

    @Autowired
    private TblOrderService tblOrderService;

    @Autowired
    private TblOrderItemService tblOrderItemService;

    @GetMapping("/queryUsedChats")
    public Response<Integer> queryUsedChats(String userId) {
        Assert.isTrue(StringUtils.isNotEmpty(userId), "userId为空");
        TblChat tblChat = new TblChat();
        tblChat.setUserId(userId);
        int count = tblChatService.count(tblChat);
//        if(count > 10) {
//            return Response.fail("您的免费对话次数(10)已用完，请购买资源包.");
//        }
        return Response.success(count);
    }

    @GetMapping("/queryUsedDraws")
    public Response<Integer> queryUsedDraws(String userId) {
        Assert.isTrue(StringUtils.isNotEmpty(userId), "userId为空");
        TblDraw tblDraw = new TblDraw();
        tblDraw.setUserId(userId);
        int count = tblDrawService.count(tblDraw);
//        if(count > 5) {
//            return Response.fail("您的免费作画次数(5)已用完，请购买资源包.");
//        }
        return Response.success(count);
    }

    @GetMapping("/queryRemainingResources")
    public Response<Map<String, Integer>> queryRemainingResources(String userId) {
        Assert.isTrue(StringUtils.isNotEmpty(userId), "userId为空");
        Map<String, Integer> map = new HashMap<>(2);
        int chatTotal = 10;
        int drawTotal = 5;
        // 统计所有订单
        PageOrderQuery pageOrderQuery = new PageOrderQuery();
        pageOrderQuery.setUserId(userId);
        pageOrderQuery.setPayStatus(PayStatusEnum.PAYED.getStatus());
        // 不传分页就是全部查
        List<TblOrder> tblOrders = tblOrderService.pageQuery(pageOrderQuery);
        List<String> itemCodes = tblOrders.stream().map(TblOrder::getItemCode).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(itemCodes)) {
            List<TblOrderItem> tblOrderItems = tblOrderItemService.queryByCodes(itemCodes);
            int chatSum = tblOrderItems.stream()
                    .filter(item -> item.getProduct().equals(OrderItemEnum.CHATGPT.name()))
                    .mapToInt(TblOrderItem::getAmount).sum();
            int drawSum = tblOrderItems.stream()
                    .filter(item -> item.getProduct().equals(OrderItemEnum.DRAW.name()))
                    .mapToInt(TblOrderItem::getAmount).sum();
            if(chatSum > 0) {
                chatTotal = chatSum;
            }
            if(drawSum > 0) {
                drawTotal = drawSum;
            }
        }
        map.put(OrderItemEnum.CHATGPT.name(), chatTotal);
        map.put(OrderItemEnum.DRAW.name(), drawTotal);
        // 统计已用次数
        TblChat chatQuery = new TblChat();
        chatQuery.setUserId(userId);
        int usedChat = tblChatService.count(chatQuery);
        map.put("CHAT_REMAINING", Math.max(chatTotal - usedChat, 0));
        TblDraw drawQuery = new TblDraw();
        drawQuery.setUserId(userId);
        int usedDraw = tblDrawService.count(drawQuery);
        map.put("DRAW_REMAINING", Math.max(drawTotal - usedDraw, 0));
        return Response.success(map);
    }
}
