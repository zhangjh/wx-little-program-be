package me.zhangjh.wx.program.service.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblWrongs;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 2:11 PM 2023/2/23
 * @Description
 */
public interface TblWrongsService {

    TblWrongs insert(TblWrongs tblWrongs);

    List<TblWrongs> query(TblWrongs tblWrongs);

    boolean deleteById(Long id);
}
