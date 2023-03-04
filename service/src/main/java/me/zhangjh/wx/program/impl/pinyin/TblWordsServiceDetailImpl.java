package me.zhangjh.wx.program.impl.pinyin;

import me.zhangjh.wx.program.mapper.pinyin.TblWordsDetailMapper;
import me.zhangjh.wx.program.model.pinyin.TblWordsDetail;
import me.zhangjh.wx.program.service.pinyin.TblWordsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 2:18 PM 2023/2/23
 * @Description
 */
@Component
public class TblWordsServiceDetailImpl implements TblWordsDetailService {

    @Autowired
    private TblWordsDetailMapper tblWordsDetailMapper;

    @Override
    public TblWordsDetail insert(TblWordsDetail tblWordsDetail) {
        tblWordsDetailMapper.insertTblWordsDetail(tblWordsDetail);
        return tblWordsDetail;
    }

    @Override
    public List<TblWordsDetail> batchInsert(List<TblWordsDetail> tblWordsDetailList) {
        tblWordsDetailMapper.batchInsertTblWordsDetail(tblWordsDetailList);
        return tblWordsDetailList;
    }

    @Override
    public List<TblWordsDetail> query(TblWordsDetail tblWordsDetail) {
        return tblWordsDetailMapper.query(tblWordsDetail);
    }

    @Override
    public List<TblWordsDetail> queryByWordsId(Long wordsId) {
        return tblWordsDetailMapper.queryByWordsId(wordsId);
    }

    @Override
    public boolean deleteById(Long id) {
        return tblWordsDetailMapper.deleteById(id) > 0;
    }

    @Override
    public boolean clearWords(Long wordsId) {
        return tblWordsDetailMapper.clearWordsId(wordsId) > 0;
    }
}
