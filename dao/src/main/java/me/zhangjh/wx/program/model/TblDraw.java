package me.zhangjh.wx.program.model;

import lombok.Data;

import java.sql.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:29 PM 2023/2/4
 * @Description
 */
@Data
public class TblDraw {

    private Long id ;
    private Date createTime ;
    private Date modifyTime ;
    private String isDeleted ;

    /** 用户id */
    private String userId ;
    /** 关键词 */
    private String term ;
    /** 图片url */
    private String url ;

}
