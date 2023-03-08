package me.zhangjh.wx.program.order.request;

import lombok.Data;

/**
 * @author zhangjh451@midea.com
 * @date 9:48 AM 2023/3/7
 * @Description
 */
@Data
public class OrderRequest {

    private String userId;

    private String code;

    private Integer price;

    private String desc;

    /**
     * 优惠券码
     * */
    private String couponCode;
}
