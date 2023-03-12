package me.zhangjh.wx.program.mapper.order;

import me.zhangjh.wx.program.model.order.TblOrderItem;

import java.util.List;

/**
*  @author author
*/
public interface TblOrderItemMapper {

    List<TblOrderItem> queryTblOrderItem(TblOrderItem query);

    List<TblOrderItem> queryByCodes(List<String> itemCodes);
}