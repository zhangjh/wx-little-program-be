package me.zhangjh.wx.program;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.share.response.Response;
import me.zhangjh.share.util.HttpClientUtil;
import me.zhangjh.wx.program.chatgpt.request.AccountRequest;
import me.zhangjh.wx.program.constant.ProductEnum;
import me.zhangjh.wx.program.service.pinyin.WxAccountService;
import me.zhangjh.wx.program.util.WXDecryptUtil;
import me.zhangjh.wx.program.vo.WxUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author njhxzhangjihong@126.com
 * @date 3:35 PM 2023/2/13
 * @Description
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class WxController {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @Value("${wx.hanzi.appId}")
    private String hanziAppId;

    @Value("${wx.hanzi.appSecret}")
    private String hanziAppSecret;

    @Autowired
    private WxAccountService wxAccountService;

    @RequestMapping("/decodeUserInfo")
    public Response<WxUserInfo> getUserInfo(String encryptedData, String sessionKey, String iv) {
        Assert.isTrue(StringUtils.isNotEmpty(encryptedData), "加密信息为空");
        Assert.isTrue(StringUtils.isNotEmpty(sessionKey), "sessionKey为空");
        Assert.isTrue(StringUtils.isNotEmpty(iv), "iv为空");
        String userInfoStr = WXDecryptUtil.getUserInfo(encryptedData, sessionKey, iv);
        WxUserInfo wxUserInfo = JSONObject.parseObject(userInfoStr, WxUserInfo.class);
        return Response.success(wxUserInfo);
    }

    @RequestMapping("/getOpenId")
    public Response<WxUserInfo> getOpenId(String jsCode, String productType) {
        // 兼容AI文图未传场景
        if(StringUtils.isEmpty(productType)) {
            productType = ProductEnum.CHATGPT.name();
        } else {
            productType = productType.toUpperCase();
        }
        Assert.isTrue(StringUtils.isNotEmpty(jsCode), "jsCode为空");
        String url = "https://api.weixin.qq.com/sns/jscode2session?appId=";
        if(ProductEnum.CHATGPT.name().equals(productType)) {
            url += appId + "&secret=" + appSecret;
        } else if(ProductEnum.HANZI.name().equals(productType)) {
            url += hanziAppId + "&secret=" + hanziAppSecret;
        } else {
            throw new RuntimeException("不支持的产品类型");
        }
        url += "&js_code=" + jsCode + "&grant_type=authorization_code";
        String response = HttpClientUtil.sendHttp(url);
        log.info("api.weixin returned response: {}", response);
        WxUserInfo wxUserInfo = JSONObject.parseObject(response, WxUserInfo.class);
        return Response.success(wxUserInfo);
    }

    @GetMapping("/checkWxSign")
    @SneakyThrows
    public String checkWxSign(String signature, String timestamp,
                                        String nonce, String echostr) {
        Assert.isTrue(StringUtils.isNotEmpty(signature), "signature为空");
        Assert.isTrue(StringUtils.isNotEmpty(timestamp), "timestamp为空");
        Assert.isTrue(StringUtils.isNotEmpty(nonce), "nonce为空");
        Assert.isTrue(StringUtils.isNotEmpty(echostr), "echostr为空");
        String token = "wired_sheep";
        String[] temp = new String[] {token, timestamp, nonce};
        Arrays.sort(temp);
        String joined = String.join( "", temp);
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        byte[] hashBytes = sha1.digest(joined.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        if (signature.equals(hexString.toString())) {
            return echostr;
        }
        return "false";
    }

    /**
     * 后续所有的wx小程序用户都用一张表存储，AI文图暂时先不合并了
     * */
    @PostMapping("/saveWxUser")
    public Response<Void> saveWxUser(@RequestBody AccountRequest req) {
        me.zhangjh.wx.program.model.pinyin.TblAccount tblAccount =
                new me.zhangjh.wx.program.model.pinyin.TblAccount();
        Assert.isTrue(StringUtils.isNotEmpty(req.getUserId()), "userId为空");
        Assert.isTrue(StringUtils.isNotEmpty(req.getProductType()), "productType为空");
        tblAccount.setExtId(req.getUserId());
        tblAccount.setExtType(1);
        tblAccount.setAvatar(req.getAvatarUrl());
        tblAccount.setName(req.getNickName());
        tblAccount.setProductType(req.getProductType());
        wxAccountService.insert(tblAccount);
        return Response.success(null);
    }
}
