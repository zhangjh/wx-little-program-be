package me.zhangjh.wx.program.model.chatgpt;

import lombok.Data;
import me.zhangjh.wx.program.model.common.PageQuery;

/**
 * @author njhxzhangjihong@126.com
 * @date 11:47 PM 2023/3/6
 * @Description
 */
@Data
public class PageDrawQuery extends PageQuery {

    private String userId;
}
