package me.zhangjh.wx.program.model.pinyin;

import lombok.Getter;

/**
 * @author zhangjh451@midea.com
 * @date 11:14 AM 2023/2/23
 * @Description
 */
@Getter
public enum OrderItemEnum {
    CUSTOMIZE(0, "自定义生字本"),
    WRONGS(1, "错题本"),
    ;

    private final Integer code;

    private final String desc;

    OrderItemEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
