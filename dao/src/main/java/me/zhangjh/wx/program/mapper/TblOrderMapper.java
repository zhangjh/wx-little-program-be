package me.zhangjh.wx.program.mapper;

import me.zhangjh.wx.program.model.TblOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
*  @author author
*/
@Mapper
public interface TblOrderMapper {

    int insertTblOrder(TblOrder object);

    int updateTblOrder(TblOrder object);

    List<TblOrder> queryTblOrder(TblOrder object);

}