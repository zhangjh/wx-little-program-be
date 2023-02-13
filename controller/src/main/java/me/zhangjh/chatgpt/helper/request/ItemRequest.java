package me.zhangjh.chatgpt.helper.request;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * @author zhangjh451@midea.com
 * @date 4:04 PM 2023/2/6
 * @Description
 */
@Data
public class ItemRequest extends BaseRequest {

    private String itemId;

    private Integer type;

    private String tag;

    private Integer shareType;

    private String target;

    private Integer pageIndex;

    private Integer pageSize;

    // 是否获取全部的标记
    private Boolean getAllFlag = false;

    @Override
    public void check() {
        Assert.isTrue(StringUtils.isNotEmpty(itemId), "itemId为空");
    }
}
