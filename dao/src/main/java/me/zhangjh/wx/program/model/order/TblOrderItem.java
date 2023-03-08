package me.zhangjh.wx.program.model.order;

import lombok.Data;

import java.util.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 5:40 PM 2023/3/3
 * @Description
 */
@Data
public class TblOrderItem {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private Integer isDeleted;

    /**
     * 购买项名称
     * */
    private String name;

    /**
     * 购买项唯一code
     * */
    private String code;

    /**
     * 该单项归属哪个产品 { @link me.zhangjh.wx.program.constant.ProductEnum }
     * */
    private String product;

    /**
     * 具体到分
     */
    private Long price;

    /**
     * 购买项次数
     * */
    private Integer amount;

    /**
     * 订单项描述，如AI聊天、AI作图，具体购买项的内容描述
     */
    private String desc;
}
