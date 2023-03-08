package me.zhangjh.wx.program.constant;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author zhangjh451@midea.com
 * @date 10:59 PM 2023/3/7
 * @Description
 */
@Getter
public enum PayStatusEnum {

    UNPAID(0, "未支付"),
    PAYED(1, "已支付"),
    CANCEL(2, "已取消"),
    ;

    private Integer status;
    private String desc;

    PayStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static String getDescByStatus(Integer status) {
        return Arrays.stream(PayStatusEnum.values())
                .filter(item -> item.status.equals(status))
                .findAny().get().desc;
    }
}
