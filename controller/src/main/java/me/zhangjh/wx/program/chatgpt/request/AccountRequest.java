package me.zhangjh.wx.program.chatgpt.request;

import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 10:41 AM 2023/2/16
 * @Description
 */
@Data
public class AccountRequest {

    private String userId;

    private Integer extType;

    private String nickName;

    private String avatarUrl;

    /**
     * @link ProductTypeEnum
     * */
    private String productType;
}
