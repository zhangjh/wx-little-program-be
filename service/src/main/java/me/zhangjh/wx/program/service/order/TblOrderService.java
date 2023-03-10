package me.zhangjh.wx.program.service.order;

import me.zhangjh.wx.program.model.order.PageOrderQuery;
import me.zhangjh.wx.program.model.order.TblOrder;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 2:12 PM 2023/2/23
 * @Description
 */
public interface TblOrderService {

    TblOrder insert(TblOrder tblOrder);

    TblOrder query(String orderId);

    List<TblOrder> pageQuery(PageOrderQuery orderQuery);

    int update(TblOrder tblOrder);

    int count(PageOrderQuery orderQuery);
}
