package me.zhangjh.wx.program.pinyin;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.share.response.Response;
import me.zhangjh.wx.program.model.pinyin.TblAccount;
import me.zhangjh.wx.program.model.pinyin.TblWords;
import me.zhangjh.wx.program.model.pinyin.TblWordsDetail;
import me.zhangjh.wx.program.model.pinyin.TblWrongs;
import me.zhangjh.wx.program.service.pinyin.*;
import me.zhangjh.wx.program.util.Utils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.*;

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
    private PinyinService pinyinService;

    @Autowired
    private WxAccountService wxAccountService;

    @Autowired
    private TblWrongsService tblWrongsService;

    @Autowired
    private TblWordsService tblWordsService;

    @Autowired
    private TblWordsDetailService tblWordsDetailService;

    @GetMapping("/getUserId")
    public Long getUserId(String openId) {
        Assert.isTrue(StringUtils.isNotEmpty(openId), "参数错误，openId为空");

        TblAccount tblAccount = wxAccountService.queryByExtId(openId, 0);
        return tblAccount.getId();
    }

    /**
     * 获取指定用户的所有错题
     */
    @GetMapping("/getWrongs")
    public Response<List<TblWrongs>> getWrongs(HttpServletRequest req) {
        String userId = req.getHeader("userId");
        Assert.isTrue(StringUtils.isNotEmpty(userId), "参数错误，userId为空");

        List<TblWrongs> tblWrongs = tblWrongsService.queryByUserId(userId);
        return Response.success(tblWrongs);
    }

    /**
     * 获取指定用户的所有生字本
     */
    @GetMapping("/getWords")
    public Response<List<TblWords>> getWords(HttpServletRequest req) {
        String userId = req.getHeader("userId");
        Assert.isTrue(StringUtils.isNotEmpty(userId), "参数错误，userId为空");

        List<TblWords> tblWords = tblWordsService.queryByUserId(userId);

        return Response.success(tblWords);
    }

    @PostMapping(value = "/getWordsDetail")
    public Response<List<Map<String, String>>> getWordsDetail(@RequestParam ArrayList<Long> wordsIdList,
                                                              HttpServletRequest req) {
        String userId = req.getHeader("userId");
        Assert.isTrue(StringUtils.isNotEmpty(userId), "参数错误，userId为空");
        Assert.isTrue(CollectionUtils.isNotEmpty(wordsIdList), "wordsIdList为空");

        List<Map<String, String>> result = new ArrayList<>();
        for (Long wordsId : wordsIdList) {
            List<TblWordsDetail> tblWordsDetails = tblWordsDetailService.queryByWordsId(wordsId);
            Set<String> characterSet = new HashSet<>();
            StringBuilder pinyinSb = new StringBuilder();
            for (TblWordsDetail tblWordsDetail : tblWordsDetails) {
                characterSet.add(tblWordsDetail.getCharacter());
                pinyinSb.append(tblWordsDetail.getPinyin()).append(" ");
            }
            String pinyin = pinyinSb.toString();
            String character = StringUtils.join(characterSet, " ");
            Map<String, String> map = new HashMap<>();
            map.put("wordsId", String.valueOf(wordsId));
            map.put("characters", character);
            map.put("pinyins", pinyin);
            result.add(map);
        }

        return Response.success(result);
    }

    /**
     * 校验给定的输入是否都是汉字，返回不是的
     */
    @PostMapping("/checkCharacters")
    public Response<Character> checkCharacters(String characters, HttpServletRequest req) {
        String userId = req.getHeader("userId");
        Assert.isTrue(StringUtils.isNotEmpty(userId), "参数错误，userId为空");
        Assert.isTrue(StringUtils.isNotEmpty(characters), "参数错误，characters为空");

        characters = URLDecoder.decode(characters, Charset.defaultCharset());

        for (String character : characters.trim().split("")) {
            Character ch = character.charAt(0);
            if(!Utils.isCharacter(ch)) {
                return Response.success(ch);
            }
        }
        return Response.success(null);
    }

    @PostMapping("/saveWords")
    public Response<Void> saveWords(String wordsName, HttpServletRequest req) {
        String userId = req.getHeader("userId");
        Assert.isTrue(StringUtils.isNotEmpty(userId), "参数错误，userId为空");
        Assert.isTrue(StringUtils.isNotEmpty(wordsName), "参数错误，wordsName为空");

        TblWords tblWords = new TblWords();
        tblWords.setName(wordsName);
        tblWords.setUserId(userId);
        List<TblWords> existWords = tblWordsService.query(tblWords);
        if(CollectionUtils.isNotEmpty(existWords)) {
            return Response.fail(wordsName + "已经存在");
        }
        tblWordsService.insert(tblWords);
        return Response.success(null);
    }

    @GetMapping("/deleteWords")
    @Transactional
    public Response<Void> deleteWords(Long id, HttpServletRequest req) {
        String userId = req.getHeader("userId");
        Assert.isTrue(StringUtils.isNotEmpty(userId), "参数错误，userId为空");
        Assert.isTrue(id != null, "参数错误，id为空");

        tblWordsService.deleteById(id);
        tblWordsDetailService.clearWords(id);
        return Response.success(null);
    }

    @PostMapping("/saveWordsDetail")
    @SneakyThrows
    public Response<String> saveWordsDetail(Long wordsId, String characters, HttpServletRequest req) {
        characters = URLDecoder.decode(characters, Charset.defaultCharset());
        List<TblWordsDetail> tblWordsDetails = new ArrayList<>();
        for (String character : characters.trim().split("")) {
            Character ch = character.charAt(0);
            if(!Utils.isCharacter(ch)) {
                return Response.fail(character + "不是汉字");
            }
            List<String> pinyinList = pinyinService.transfer(character);
            for (String pinyin : pinyinList) {
                TblWordsDetail tblWordsDetail = new TblWordsDetail();
                tblWordsDetail.setCharacter(character);
                tblWordsDetail.setWordsId(wordsId);
                tblWordsDetail.setPinyin(pinyin);
                tblWordsDetails.add(tblWordsDetail);
            }
        }
        tblWordsDetailService.batchInsert(tblWordsDetails);
        return Response.success(null);
    }
}
