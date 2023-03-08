package me.zhangjh.wx.program.order.response;

import lombok.Data;

import java.util.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 10:56 PM 2023/3/7
 * @Description
 */
@Data
public class OrderVO {

    private String id;

    private String orderId;

    private Date createTime;

    private String payStatus;

    private String itemCode;

    private String itemDesc;

    private Integer price;

    private Integer total;
}
