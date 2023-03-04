package me.zhangjh.wx.program.model.pinyin;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhangjh451@midea.com
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
     * 用户id
     */
    private String userId;
}
