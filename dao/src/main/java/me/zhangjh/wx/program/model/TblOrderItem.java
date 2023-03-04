package me.zhangjh.wx.program.model;

import lombok.Data;

/**
 * @author zhangjh451@midea.com
 * @date 5:40 PM 2023/3/3
 * @Description
 */
@Data
public class TblOrderItem {

    private Long id;

    private String code;

    /**
     * 该单项归属哪个产品 { @link me.zhangjh.wx.program.model.ProductEnum }
     * */
    private String product;

    /**
     * 具体到分
     */
    private Long price;

    /**
     * 订单项描述，如AI聊天、AI作图
     */
    private String desc;
}
