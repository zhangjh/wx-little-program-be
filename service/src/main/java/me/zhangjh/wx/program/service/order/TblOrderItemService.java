package me.zhangjh.wx.program.service.order;

import me.zhangjh.wx.program.model.order.TblOrderItem;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 12:19 AM 2023/3/7
 * @Description
 */
public interface TblOrderItemService {

    List<TblOrderItem> queryOrderItems(TblOrderItem query);

    List<TblOrderItem> queryByCodes(List<String> itemCodes);
}
