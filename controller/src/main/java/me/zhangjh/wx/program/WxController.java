package me.zhangjh.wx.program;

import com.alibaba.fastjson.JSONObject;
import me.zhangjh.share.response.Response;
import me.zhangjh.share.util.HttpClientUtil;
import me.zhangjh.wx.program.util.WXDecryptUtil;
import me.zhangjh.wx.program.vo.WxUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author njhxzhangjihong@126.com
 * @date 3:35 PM 2023/2/13
 * @Description
 */
@RestController
@RequestMapping("/wx")
public class WxController {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @RequestMapping("/decodeUserInfo")
    public Response<WxUserInfo> getUserInfo(String encryptedData, String sessionKey, String iv) {
        try {
            Assert.isTrue(StringUtils.isNotEmpty(encryptedData), "加密信息为空");
            Assert.isTrue(StringUtils.isNotEmpty(sessionKey), "sessionKey为空");
            Assert.isTrue(StringUtils.isNotEmpty(iv), "iv为空");
            String userInfoStr = WXDecryptUtil.getUserInfo(encryptedData, sessionKey, iv);
            WxUserInfo wxUserInfo = JSONObject.parseObject(userInfoStr, WxUserInfo.class);
            return Response.success(wxUserInfo);
        } catch (Throwable t) {
            return Response.fail(t.getMessage());
        }
    }

    @RequestMapping("/getOpenId")
    public Response<WxUserInfo> getOpenId(String jsCode) {
        try {
            Assert.isTrue(StringUtils.isNotEmpty(jsCode), "jsCode为空");
            String url = "https://api.weixin.qq.com/sns/jscode2session?appId=";
            url += appId + "&secret=" + appSecret + "&js_code=" + jsCode + "&grant_type=authorization_code";
            String response = HttpClientUtil.sendHttp(url);
            WxUserInfo wxUserInfo = JSONObject.parseObject(response, WxUserInfo.class);
            return Response.success(wxUserInfo);
        } catch (Throwable t) {
            return Response.fail(t.getMessage());
        }
    }
}
