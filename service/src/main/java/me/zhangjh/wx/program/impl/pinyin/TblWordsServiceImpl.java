package me.zhangjh.wx.program.impl.pinyin;

import me.zhangjh.wx.program.mapper.pinyin.TblWordsMapper;
import me.zhangjh.wx.program.model.pinyin.TblWords;
import me.zhangjh.wx.program.service.pinyin.TblWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 2:18 PM 2023/2/23
 * @Description
 */
@Component
public class TblWordsServiceImpl implements TblWordsService {

    @Autowired
    private TblWordsMapper tblWordsMapper;

    @Override
    public TblWords insert(TblWords tblWords) {
        tblWordsMapper.insertTblWords(tblWords);
        return tblWords;
    }

    @Override
    public List<TblWords> query(TblWords query) {
        return tblWordsMapper.query(query);
    }

    @Override
    public boolean deleteById(Long id) {
        TblWords tblWords = new TblWords();
        tblWords.setId(id);
        tblWords.setIsDeleted(1);
        return tblWordsMapper.update(tblWords) > 0;
    }
}
