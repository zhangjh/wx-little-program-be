package me.zhangjh.wx.program.model.common;

import lombok.Data;

import java.sql.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 11:06 AM 2023/2/23
 * @Description
 */
@Data
public class TblAccount {

    private Long id ;
    private Date createTime ;
    private Date modifyTime ;
    private Integer isDeleted ;

    private String name;

    private String avatar;

    private Integer extType;

    private String extId;

    private String mobile;

    private String productType;
}
