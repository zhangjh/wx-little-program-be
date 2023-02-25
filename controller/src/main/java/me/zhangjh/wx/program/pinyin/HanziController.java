package me.zhangjh.wx.program.pinyin;

import lombok.extern.slf4j.Slf4j;
import me.zhangjh.share.response.Response;
import me.zhangjh.wx.program.model.pinyin.TblAccount;
import me.zhangjh.wx.program.model.pinyin.TblWrongs;
import me.zhangjh.wx.program.service.pinyin.TblWrongsService;
import me.zhangjh.wx.program.service.pinyin.WxAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 10:30 PM 2023/2/25
 * @Description
 */
@RestController
@RequestMapping("/wx/hanzi")
@CrossOrigin
@Slf4j
public class HanziController {

    @Autowired
    private WxAccountService wxAccountService;

    @Autowired
    private TblWrongsService tblWrongsService;

    @GetMapping("/getUserId")
    public Long getUserId(String openId) {
        Assert.isTrue(StringUtils.isNotEmpty(openId), "参数错误，openId为空");

        TblAccount tblAccount = wxAccountService.queryByExtId(openId, 0);
        return tblAccount.getId();
    }

    @GetMapping("/getWrongs")
    public Response<List<TblWrongs>> getWrongs(HttpServletRequest req) {
        String userId = req.getHeader("userId");
        Assert.isTrue(StringUtils.isNotEmpty(userId), "参数错误，userId为空");

        List<TblWrongs> tblWrongs = tblWrongsService.queryByUserId(Long.parseLong(userId));
        return Response.success(tblWrongs);
    }
}
