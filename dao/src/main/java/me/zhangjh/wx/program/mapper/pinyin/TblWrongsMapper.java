package me.zhangjh.wx.program.mapper.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblWrongs;

import java.util.List;
/**
*  @author author
*/
public interface TblWrongsMapper {

    int insertTblWrongs(TblWrongs tblWrongs);

    int update(TblWrongs tblWrongs);

    List<TblWrongs> queryByUserId(Long userId);

}