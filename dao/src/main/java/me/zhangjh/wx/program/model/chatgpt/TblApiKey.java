package me.zhangjh.wx.program.model.chatgpt;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhangjh451@midea.com
 * @date 4:27 PM 2023/2/27
 * @Description
 */
@Data
public class TblApiKey {
    private String id ;
    private Date createTime ;
    private Date modifyTime ;
    private Integer isDeleted ;

    private String email;
    private String apiKey;
}
