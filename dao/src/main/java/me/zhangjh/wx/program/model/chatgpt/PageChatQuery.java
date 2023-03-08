package me.zhangjh.wx.program.model.chatgpt;

import lombok.Data;
import me.zhangjh.wx.program.model.PageQuery;

/**
 * @author njhxzhangjihong@126.com
 * @date 11:43 PM 2023/3/6
 * @Description
 */
@Data
public class PageChatQuery extends PageQuery {

    private String userId;
}
