package me.zhangjh.wx.program.service.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblWrongs;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 2:11 PM 2023/2/23
 * @Description
 */
public interface TblWrongsService {

    TblWrongs insert(TblWrongs tblWrongs);

    List<TblWrongs> queryByUserId(Long userId);

    boolean deleteById(Long id);
}
