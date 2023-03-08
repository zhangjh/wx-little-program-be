package me.zhangjh.wx.program.model.pinyin;

import lombok.Data;

import java.sql.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 11:11 AM 2023/2/23
 * @Description
 */
@Data
public class TblWrongs {

    private Long id ;
    private Date createTime ;
    private Date modifyTime ;
    private Integer isDeleted ;

    private String userId;

    private String character;

    private String pinyin;
}
