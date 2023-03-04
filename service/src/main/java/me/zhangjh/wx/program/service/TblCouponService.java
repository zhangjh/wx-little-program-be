package me.zhangjh.wx.program.service;

import me.zhangjh.wx.program.model.TblCoupon;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 8:38 PM 2023/3/4
 * @Description
 */
public interface TblCouponService {

    List<TblCoupon> query(TblCoupon query);

    TblCoupon queryByCode(String couponCode);

    boolean setCouponUsed(String couponCode);
}
