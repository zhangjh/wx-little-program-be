package me.zhangjh.wx.program.service.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblWords;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 2:09 PM 2023/2/23
 * @Description
 */
public interface TblWordsService {

    TblWords insert(TblWords tblWords);

    List<TblWords> query(TblWords tblWords);

    boolean deleteById(Long id);
}