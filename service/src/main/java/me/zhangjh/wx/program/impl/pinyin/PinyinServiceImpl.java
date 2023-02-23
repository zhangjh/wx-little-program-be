package me.zhangjh.wx.program.impl.pinyin;

import me.zhangjh.wx.program.service.pinyin.PinyinService;
import me.zhangjh.wx.program.util.Utils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhangjh451@midea.com
 * @date 11:30 AM 2023/2/22
 * @Description
 */
@Component
public class PinyinServiceImpl implements PinyinService {

    @Override
    public List<String> transfer(String character) throws BadHanyuPinyinOutputFormatCombination {
        Assert.isTrue(StringUtils.isNotEmpty(character), "汉字为空");

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        char[] chars = character.trim().toCharArray();
        List<String> result = new ArrayList<>();
        for (char ch : chars) {
            Assert.isTrue(Utils.isCharacter(ch), ch + "不是汉字");
            String[] ret = PinyinHelper.toHanyuPinyinStringArray(ch, format);
            // 如果是多音字的话同时加入转换结果
            result.addAll(List.of(ret));
        }
        return result;
    }
}
