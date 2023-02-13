package me.zhangjh.chatgpt.helper.model;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhangjh451@midea.com
 * @date 4:31 PM 2023/2/4
 * @Description
 */
@Data
public class TblAccount {
    private String id ;
    private Date createTime ;
    private Date modifyTime ;
    private String isDeleted ;

    private String avatar;

    private Integer extType;

    private String extId;

    private String mobile;
}
