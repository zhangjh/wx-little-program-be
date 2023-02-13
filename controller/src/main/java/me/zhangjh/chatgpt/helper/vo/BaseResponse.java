package me.zhangjh.chatgpt.helper.vo;

import lombok.Data;

/**
 * @author zhangjh451@midea.com
 * @date 7:35 PM 2023/2/4
 * @Description
 */
@Data
public class BaseResponse {

    private Boolean success = true;

    private String errorMsg;
}
