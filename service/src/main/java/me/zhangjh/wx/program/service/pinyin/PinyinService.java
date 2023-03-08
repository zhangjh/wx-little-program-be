package me.zhangjh.wx.program.service.pinyin;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 11:28 AM 2023/2/22
 * @Description
 */
public interface PinyinService {

    /**
     * transfer one character to pinyin
     */
    List<String> transfer(String character) throws BadHanyuPinyinOutputFormatCombination;
}
