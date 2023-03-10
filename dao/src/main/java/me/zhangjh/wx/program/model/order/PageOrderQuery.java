package me.zhangjh.wx.program.model.order;

import lombok.Data;
import me.zhangjh.wx.program.model.common.PageQuery;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 12:03 AM 2023/3/8
 * @Description
 */
@Data
public class PageOrderQuery extends PageQuery {

    private Long id;

    private String itemCode;

    private String userId;

    private String orderId;

    private Integer payStatus;

    private List<Integer> payStatusList;

    private String couponCode;
}
