package me.zhangjh.wx.program.mapper;

import me.zhangjh.wx.program.model.TblCoupon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
*  @author author
*/
@Mapper
public interface TblCouponMapper {

    int insertTblCoupon(TblCoupon object);

    int setCouponUsed(String code);

    List<TblCoupon> queryTblCoupon(TblCoupon object);

}