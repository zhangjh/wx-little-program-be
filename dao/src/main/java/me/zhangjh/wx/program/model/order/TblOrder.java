package me.zhangjh.wx.program.model.order;

import lombok.Data;

import java.sql.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 11:12 AM 2023/2/23
 * @Description
 */
@Data
public class TblOrder {

    private String id ;
    private Date createTime ;
    private Date modifyTime ;
    private Integer isDeleted ;

    private String userId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单购买的内容
     * */
    private String itemCode;

    /**
     * 支付状态，0：待支付，1：已支付，2：已取消，3：已退款
     * */
    private Integer payStatus;

    /**
     * 订单金额
     * */
    private Integer price;

    private String couponCode;
}
