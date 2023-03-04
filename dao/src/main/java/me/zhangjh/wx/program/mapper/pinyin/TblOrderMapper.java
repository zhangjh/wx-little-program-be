package me.zhangjh.wx.program.mapper.pinyin;

import me.zhangjh.wx.program.model.TblOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
*  @author author
*/
@Mapper
public interface TblOrderMapper {

    int insertTblOrder(TblOrder tblOrder);

    int update(TblOrder tblOrder);

    List<TblOrder> query(TblOrder tblOrder);

}