package me.zhangjh.chatgpt.helper.model;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhangjh451@midea.com
 * @date 10:08 AM 2023/2/14
 * @Description
 */
@Data
public class TblQuestion {

    private Long id;
    private Date createTime ;
    private Date modifyTime ;
    private String isDeleted ;

    private String question;

}
