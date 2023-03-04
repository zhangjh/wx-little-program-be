package me.zhangjh.wx.program.model.pinyin;

import lombok.Data;

import java.sql.Date;

/**
 * @author zhangjh451@midea.com
 * @date 6:54 PM 2023/2/28
 * @Description
 */
@Data
public class TblWordsDetail {

    private Long id ;
    private Date createTime ;
    private Date modifyTime ;
    private Integer isDeleted ;

    /**
     * 生字
     */
    private String character;
    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 该汉字属于哪个生字本
     * */
    private Long wordsId;
}
