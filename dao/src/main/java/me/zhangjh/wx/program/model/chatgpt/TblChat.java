package me.zhangjh.wx.program.model.chatgpt;

import lombok.Data;

import java.sql.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:33 PM 2023/2/4
 * @Description
 */
@Data
public class TblChat {

    private Long id ;
    private Date createTime ;
    private Date modifyTime ;
    private Integer isDeleted ;

    private String userId;

    private String question;

    private String answer;
}
