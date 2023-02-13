package me.zhangjh.chatgpt.helper.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 4:55 PM 2023/2/4
 * @Description
 */
@Data
public class PageDTO<T> {

    private List<T> data;

    private Integer total;
}
