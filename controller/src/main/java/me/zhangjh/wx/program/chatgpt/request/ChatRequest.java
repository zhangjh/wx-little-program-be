package me.zhangjh.wx.program.chatgpt.request;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author njhxzhangjihong@126.com
 * @date 2:59 PM 2023/2/6
 * @Description
 */
@Data
public class ChatRequest extends BaseRequest {

    /**
     * 本次提问问题
     */
    private String question;

    /**
     * 提问相关的上下文信息，key是问题，value是回答，只适用于ChatGpt模型
     * */
    private Map<String, String> contextMap;

    @Override
    public void check() {
        Assert.isTrue(StringUtils.isNotEmpty(question), "小主想问点啥？");
    }
}
