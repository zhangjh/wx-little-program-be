package me.zhangjh.wx.program.model.chatgpt;

import lombok.Data;
import me.zhangjh.wx.program.model.PageQuery;

/**
 * @author zhangjh451@midea.com
 * @date 11:43 PM 2023/3/6
 * @Description
 */
@Data
public class PageChatQuery extends PageQuery {

    private String userId;
}
