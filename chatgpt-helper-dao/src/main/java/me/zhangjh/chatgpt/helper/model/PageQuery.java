package me.zhangjh.chatgpt.helper.model;

import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:36 PM 2023/2/4
 * @Description
 */
@Data
public class PageQuery<T> {

    // 页码
    private Integer pageIndex = 1;

    // 每页条数
    private Integer pageSize = 10;

    private T data;
}
