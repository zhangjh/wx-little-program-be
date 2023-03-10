package me.zhangjh.wx.program.mapper.pinyin;

import me.zhangjh.wx.program.model.common.TblAccount;
import org.apache.ibatis.annotations.Mapper;

/**
*  @author author
*/
@Mapper
public interface WxAccountMapper {

    int insertTblAccount(TblAccount tblAccount);

    int update(TblAccount tblAccount);

    TblAccount queryById(Long id);

    TblAccount query(TblAccount tblAccount);

    TblAccount queryByExtId(String extId, Integer extType);
}