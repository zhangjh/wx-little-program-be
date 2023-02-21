package me.zhangjh.wx.program.request;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * @author njhxzhangjihong@126.com
 * @date 3:07 PM 2023/2/6
 * @Description
 */
@Data
public class DrawRequest extends BaseRequest {
    private String term;

    @Override
    public void check() {
        Assert.isTrue(StringUtils.isNotEmpty(term), "巧妇难为无米之炊，小主给个关键词吧");
    }
}
