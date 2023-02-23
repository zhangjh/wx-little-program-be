package me.zhangjh.wx.program.impl.pinyin;

import me.zhangjh.wx.program.mapper.pinyin.TblOrderMapper;
import me.zhangjh.wx.program.model.pinyin.TblOrder;
import me.zhangjh.wx.program.service.pinyin.TblOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangjh451@midea.com
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
    public List<TblOrder> query(TblOrder query) {
        return tblOrderMapper.query(query);
    }
}
