package me.zhangjh.wx.program.mapper.order;

import me.zhangjh.wx.program.model.order.TblCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
*  @author author
*/
public interface TblCouponMapper {

    int insertTblCoupon(TblCoupon object);

    int insertBatch(List<TblCoupon> tblCoupons);

    int setCouponUsed(String code);

    List<TblCoupon> queryTblCoupon(TblCoupon object);

}