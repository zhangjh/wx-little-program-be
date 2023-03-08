package me.zhangjh.wx.program.util;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author njhxzhangjihong@126.com
 * @date 2:06 PM 2023/3/7
 * @Description
 */
@Data
public class Authorization {

    private String signature;

    @JSONField(name = "nonce_str")
    private String nonce;

    @JSONField(name = "serial_no")
    private String serialNo;

    private String mchid;

    private String timestamp;

    /**
     * prepay_id
     * */
    @JSONField(name = "package")
    private String pack;

    @Override
    public String toString() {
        return "signature=\"" + this.signature + "\"," +
                "nonce_str=\"" + this.nonce + "\"," +
                "serial_no=\"" + this.serialNo + "\"," +
                "mchid=\"" + this.mchid + "\"," +
                "timestamp=\"" + this.timestamp + "\"";
    }
}
