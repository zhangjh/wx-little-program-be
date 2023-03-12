package me.zhangjh.wx.program.mapper.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblWords;

import java.util.List;
/**
*  @author author
*/
public interface TblWordsMapper {

    int insertTblWords(TblWords tblWords);

    int update(TblWords tblWords);

    List<TblWords> query(TblWords tblWords);

}