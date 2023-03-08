package me.zhangjh.wx.program.model;

import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:36 PM 2023/2/4
 * @Description
 */
@Data
public class PageQuery {

    /**
     * 页码
     */
    private Integer pageIndex = 1;

    /**
     * 每页条数
     */
    private Integer pageSize = 10;
}
