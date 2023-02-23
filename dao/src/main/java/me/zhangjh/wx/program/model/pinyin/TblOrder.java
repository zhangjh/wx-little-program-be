package me.zhangjh.wx.program.model.pinyin;

import lombok.Data;

import java.sql.Date;
import java.util.List;

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
     * 订单购买的内容，@link me.zhangjh.wx.program.model.pinyin.OrderItemEnum
     * */
    private List<Integer> items;
}
