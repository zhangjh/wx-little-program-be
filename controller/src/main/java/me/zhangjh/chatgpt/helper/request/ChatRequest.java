package me.zhangjh.chatgpt.helper.request;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * @author zhangjh451@midea.com
 * @date 2:59 PM 2023/2/6
 * @Description
 */
@Data
public class ChatRequest extends BaseRequest {

    private String question;

    @Override
    public void check() {
        Assert.isTrue(StringUtils.isNotEmpty(question), "小主想问点啥？");
    }
}
