package me.zhangjh.chatgpt.helper.vo;

import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 7:31 PM 2023/2/4
 * @Description
 */
@Data
public class Response<T> extends BaseResponse {

    private T data;

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(String errorMsg) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorMsg(errorMsg);
        return response;
    }
}
