package me.zhangjh.wx.program.mapper;

import me.zhangjh.wx.program.model.TblOrder;

import java.util.List;

/**
*  @author author
*/
public interface TblOrderMapper {

    int insertTblOrder(TblOrder object);

    int updateTblOrder(TblOrder object);

    List<TblOrder> queryTblOrder(TblOrder object);

}