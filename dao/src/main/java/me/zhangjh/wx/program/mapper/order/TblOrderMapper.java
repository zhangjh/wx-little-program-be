package me.zhangjh.wx.program.mapper.order;

import me.zhangjh.wx.program.model.order.PageOrderQuery;
import me.zhangjh.wx.program.model.common.PageQuery;
import me.zhangjh.wx.program.model.order.TblOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
*  @author author
*/
@Mapper
public interface TblOrderMapper {

    int insertTblOrder(TblOrder object);

    int updateTblOrder(TblOrder object);

    TblOrder queryByOrderId(String orderId);

    List<TblOrder> queryTblOrder(PageOrderQuery query);

    /**
     * 虽然入参使用了分页的query，但实际统计total没有用分页参数
     * */
    int count(PageQuery query);

}