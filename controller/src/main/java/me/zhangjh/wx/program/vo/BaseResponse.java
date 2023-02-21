package me.zhangjh.wx.program.vo;

import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 7:35 PM 2023/2/4
 * @Description
 */
@Data
public class BaseResponse {

    private Boolean success = true;

    private String errorMsg;
}
