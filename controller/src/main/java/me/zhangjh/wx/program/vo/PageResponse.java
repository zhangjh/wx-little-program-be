package me.zhangjh.wx.program.vo;

import lombok.Data;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 7:32 PM 2023/2/4
 * @Description
 */
@Data
public class PageResponse<T> extends BaseResponse {

    private List<T> data;

    private Integer total;

    public PageResponse<T> success(List<T> data) {
        PageResponse<T> response = new PageResponse<>();
        response.setData(data);
        return response;
    }

    public PageResponse<T> fail(String errorMsg) {
        PageResponse<T> response = new PageResponse<>();
        response.setSuccess(false);
        response.setErrorMsg(errorMsg);
        return response;
    }
}
