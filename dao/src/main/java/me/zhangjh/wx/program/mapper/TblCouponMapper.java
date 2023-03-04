package me.zhangjh.wx.program.mapper;

import me.zhangjh.wx.program.model.TblCoupon;

import java.util.List;

/**
*  @author author
*/
public interface TblCouponMapper {

    int insertTblCoupon(TblCoupon object);

    int setCouponUsed(String code);

    List<TblCoupon> queryTblCoupon(TblCoupon object);

}