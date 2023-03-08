package me.zhangjh.wx.program.model.chatgpt;

import lombok.Data;

import java.sql.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 10:08 AM 2023/2/14
 * @Description
 */
@Data
public class TblQuestion {

    private Long id;
    private Date createTime ;
    private Date modifyTime ;
    private Integer isDeleted ;

    private String question;

}
