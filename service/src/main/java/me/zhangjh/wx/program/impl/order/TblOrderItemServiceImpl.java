package me.zhangjh.wx.program.impl.order;

import me.zhangjh.wx.program.mapper.order.TblOrderItemMapper;
import me.zhangjh.wx.program.model.order.TblOrderItem;
import me.zhangjh.wx.program.service.order.TblOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 12:20 AM 2023/3/7
 * @Description
 */
@Component
public class TblOrderItemServiceImpl implements TblOrderItemService {

    @Autowired
    private TblOrderItemMapper tblOrderItemMapper;

    @Override
    public List<TblOrderItem> queryOrderItems(TblOrderItem query) {
        return tblOrderItemMapper.queryTblOrderItem(query);
    }

    @Override
    public List<TblOrderItem> queryByCodes(List<String> itemCodes) {
        return tblOrderItemMapper.queryByCodes(itemCodes);
    }
}
