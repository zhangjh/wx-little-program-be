package me.zhangjh.wx.program.model;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhangjh451@midea.com
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
     * uuid
     */
    private Long orderId;

    /**
     * 订单购买的内容，@link me.zhangjh.wx.program.model.OrderItemEnum
     * */
    private Integer itemId;

    /**
     * 支付状态，0：待支付，1：已支付，2：已取消，3：已退款
     * */
    private Integer payStatus;

    /**
     * 订单金额
     * */
    private Integer amount;
}
