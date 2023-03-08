package me.zhangjh.wx.program.impl.order;

import me.zhangjh.wx.program.mapper.order.TblOrderMapper;
import me.zhangjh.wx.program.model.PageOrderQuery;
import me.zhangjh.wx.program.model.order.TblOrder;
import me.zhangjh.wx.program.service.order.TblOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 2:16 PM 2023/2/23
 * @Description
 */
@Component
public class TblOrderServiceImpl implements TblOrderService {

    @Autowired
    private TblOrderMapper tblOrderMapper;

    @Override
    public TblOrder insert(TblOrder tblOrder) {
        tblOrderMapper.insertTblOrder(tblOrder);
        return tblOrder;
    }

    @Override
    public TblOrder query(String orderId) {
        return tblOrderMapper.queryByOrderId(orderId);
    }

    @Override
    public List<TblOrder> pageQuery(PageOrderQuery orderQuery) {
        return tblOrderMapper.queryTblOrder(orderQuery);
    }

    @Override
    public int update(TblOrder tblOrder) {
        return tblOrderMapper.updateTblOrder(tblOrder);
    }

    @Override
    public int count(PageOrderQuery orderQuery) {
        return tblOrderMapper.count(orderQuery);
    }
}
