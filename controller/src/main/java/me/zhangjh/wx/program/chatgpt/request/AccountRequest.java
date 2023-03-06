package me.zhangjh.wx.program.chatgpt.request;

import lombok.Data;

/**
 * @author zhangjh451@midea.com
 * @date 10:41 AM 2023/2/16
 * @Description
 */
@Data
public class AccountRequest {

    private String userId;

    private String nickName;

    private String avatarUrl;

    /**
     * @link ProductTypeEnum
     * */
    private String productType;
}
