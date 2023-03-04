package me.zhangjh.wx.program.impl;

import me.zhangjh.wx.program.mapper.TblCouponMapper;
import me.zhangjh.wx.program.model.TblCoupon;
import me.zhangjh.wx.program.service.TblCouponService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 8:56 PM 2023/3/4
 * @Description
 */
@Component
public class TblCouponServiceImpl implements TblCouponService {

    @Autowired
    private TblCouponMapper tblCouponMapper;

    @Override
    public List<TblCoupon> query(TblCoupon query) {
        return tblCouponMapper.queryTblCoupon(query);
    }

    @Override
    public TblCoupon queryByCode(String couponCode) {
        Assert.isTrue(StringUtils.isNotEmpty(couponCode), "优惠券为空");
        TblCoupon query = new TblCoupon();
        query.setCode(couponCode);
        List<TblCoupon> tblCoupons = tblCouponMapper.queryTblCoupon(query);
        Assert.isTrue(tblCoupons.size() == 1, "优惠券数目不符合预期");
        return tblCoupons.get(0);
    }

    @Override
    public boolean setCouponUsed(String couponCode) {
        return tblCouponMapper.setCouponUsed(couponCode) > 0;
    }
}
