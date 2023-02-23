package me.zhangjh.wx.program.mapper.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblWords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
*  @author author
*/
@Mapper
public interface TblWordsMapper {

    int insertTblWords(TblWords tblWords);

    int update(TblWords tblWords);

    List<TblWords> query(TblWords tblWords);

}