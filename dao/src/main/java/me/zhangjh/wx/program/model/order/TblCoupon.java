package me.zhangjh.wx.program.model.order;

import lombok.Data;

import java.sql.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 5:47 PM 2023/3/3
 * @Description
 */
@Data
public class TblCoupon {

    private String id ;
    private Date createTime ;
    private Date modifyTime ;
    private Integer isDeleted ;

    /**
     * 优惠券编号，5位uuid
     * */
    private String code;

    /**
     * 是否使用
     * */
    private Integer used;

    /**
     * 券类型 { @link me.zhangjh.wx.program.constant.CouponEnum }
     * */
    private String type;

    /**
     * 如果是折扣券，折扣券额，*100后的数值
     */
    private Integer discount;


}
