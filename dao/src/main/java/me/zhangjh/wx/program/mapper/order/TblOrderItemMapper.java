package me.zhangjh.wx.program.mapper.order;

import me.zhangjh.wx.program.model.order.TblOrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
*  @author author
*/
@Mapper
public interface TblOrderItemMapper {

    List<TblOrderItem> queryTblOrderItem(TblOrderItem query);

    List<TblOrderItem> queryByCodes(List<String> itemCodes);
}