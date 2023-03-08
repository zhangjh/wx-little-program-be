package me.zhangjh.wx.program.constant;

import lombok.Getter;

/**
 * @author njhxzhangjihong@126.com
 * @date 11:14 AM 2023/2/23
 * @Description
 */
@Getter
public enum OrderItemEnum {
    // 汉字助手的功能项
    CUSTOMIZE(0, "自定义生字本"),
    WRONGS(1, "错题本"),
    // AI文图的功能项
    CHATGPT(2, "AI聊天"),
    DRAW(3, "AI作图"),
    ;

    private final Integer code;

    private final String desc;

    OrderItemEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
