package me.zhangjh.wx.program.model.pinyin;

import lombok.Data;

import java.sql.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 11:08 AM 2023/2/23
 * @Description
 */
@Data
public class TblWords {

    private Long id ;
    private Date createTime ;
    private Date modifyTime ;
    private Integer isDeleted ;

    /**
     * 生字本名称
     */
    private String name;
    /**
     * 生字
     */
    private String character;
    /**
     * 拼音
     */
    private String pinyin;
    /**
     * 用户id
     */
    private String userId;
}
