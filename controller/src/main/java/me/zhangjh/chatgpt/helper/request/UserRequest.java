package me.zhangjh.chatgpt.helper.request;

import lombok.Data;

/**
 * @author zhangjh451@midea.com
 * @date 3:15 PM 2023/2/6
 * @Description
 */
@Data
public class UserRequest extends BaseRequest {

    private Integer pageIndex;

    private Integer pageSize;

    @Override
    public void check() {

    }
}
