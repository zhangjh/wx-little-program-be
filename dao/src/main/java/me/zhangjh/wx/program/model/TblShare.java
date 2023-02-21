package me.zhangjh.wx.program.model;

import lombok.Data;

import java.sql.Date;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:34 PM 2023/2/4
 * @Description
 */
@Data
public class TblShare {

    private Long id ;
    private Date createTime ;
    private Date modifyTime ;
    private String isDeleted ;

    private String userId;

    /**
     * 作品类型：文本或者图画
     * */
    private Integer type;

    private String itemId;

    private String tag;

    private Integer shareType;

    private String target;

    /** 默认不公开
     * 0:待审核公开，1:审核通过公开*/
    private Integer bizStatus = 0;
}
