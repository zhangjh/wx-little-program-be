package me.zhangjh.chatgpt.helper.model;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhangjh451@midea.com
 * @date 4:29 PM 2023/2/4
 * @Description
 */
@Data
public class TblDraw {

    private String id ;
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
