package me.zhangjh.wx.program.mapper.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblWordsDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
*  @author author
*/
@Mapper
public interface TblWordsDetailMapper {

    int insertTblWordsDetail(TblWordsDetail tblWordsDetail);

    int batchInsertTblWordsDetail(List<TblWordsDetail> tblWordsDetailList);

    int update(TblWordsDetail tblWordsDetail);

    List<TblWordsDetail> query(TblWordsDetail tblWordsDetail);

    List<TblWordsDetail> queryByWordsId(Long wordsId);

    int deleteById(Long id);

    int clearWordsId(Long wordsId);
}