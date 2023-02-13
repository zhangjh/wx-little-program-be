package me.zhangjh.chatgpt.helper.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author zhangjh451@midea.com
 * @date 3:43 PM 2023/2/13
 * @Description
 */
@Data
public class WxUserInfo {

    @JSONField(name = "open_id")
    private String openId;

    @JSONField(name = "session_key")
    private String sessionKey;
}
