package me.zhangjh.wx.program.service.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblWordsDetail;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 2:09 PM 2023/2/23
 * @Description
 */
public interface TblWordsDetailService {

    TblWordsDetail insert(TblWordsDetail tblWordsDetail);

    List<TblWordsDetail> batchInsert(List<TblWordsDetail> tblWordsDetailList);

    List<TblWordsDetail> query(TblWordsDetail tblWordsDetail);

    List<TblWordsDetail> queryByWordsId(Long wordsId);

    boolean deleteById(Long id);

    boolean clearWords(Long wordsId);
}
