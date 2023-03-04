package me.zhangjh.wx.program.mapper.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblWrongs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
*  @author author
*/
@Mapper
public interface TblWrongsMapper {

    int insertTblWrongs(TblWrongs tblWrongs);

    int update(TblWrongs tblWrongs);

    List<TblWrongs> queryByUserId(String userId);

}