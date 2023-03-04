package me.zhangjh.wx.program.service;

import me.zhangjh.wx.program.model.TblOrder;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 2:12 PM 2023/2/23
 * @Description
 */
public interface TblOrderService {

    TblOrder insert(TblOrder tblOrder);

    List<TblOrder> query(TblOrder query);
}
