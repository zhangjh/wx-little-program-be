package me.zhangjh.wx.program.impl.order;

import me.zhangjh.share.util.BizUtil;
import me.zhangjh.wx.program.mapper.order.TblCouponMapper;
import me.zhangjh.wx.program.constant.CouponEnum;
import me.zhangjh.wx.program.model.order.TblCoupon;
import me.zhangjh.wx.program.service.order.TblCouponService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author njhxzhangjihong@126.com
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
        query.setUsed(0);
        List<TblCoupon> tblCoupons = tblCouponMapper.queryTblCoupon(query);
        return CollectionUtils.isNotEmpty(tblCoupons) ? tblCoupons.get(0) : null;
    }

    @Override
    public boolean setCouponUsed(String couponCode) {
        return tblCouponMapper.setCouponUsed(couponCode) > 0;
    }

    @Override
    public List<String> generateCoupons(String couponType, Integer cnt, Integer discount) {
        List<TblCoupon> tblCoupons = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            String couponCode = BizUtil.generateRandom(6);
            TblCoupon tblCoupon = new TblCoupon();
            tblCoupon.setCode(couponCode);
            tblCoupon.setType(couponType);
            if(couponType.equals(CouponEnum.DISCOUNT.name())) {
                tblCoupon.setDiscount(discount);
            }
            tblCoupon.setUsed(0);
            tblCoupons.add(tblCoupon);
        }
        tblCouponMapper.insertBatch(tblCoupons);
        return tblCoupons.stream().map(TblCoupon::getCode).collect(Collectors.toList());
    }
}
