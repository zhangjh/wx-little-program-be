package me.zhangjh.wx.program.order.request;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author zhangjh451@midea.com
 * @date 9:53 AM 2023/3/7
 * @Description
 */
@Data
public class WxOrderRequest {

    private String userId;

    private String appid;

    private String mchid;

    private String description;

    @JSONField(name = "out_trade_no")
    private String outTradeNo;

    @JSONField(name = "notify_url")
    private String notifyUrl;

    private Integer price;

    private JSONObject payer;
}
