package me.zhangjh.wx.program.impl.pinyin;

import me.zhangjh.wx.program.mapper.pinyin.TblWrongsMapper;
import me.zhangjh.wx.program.model.pinyin.TblWrongs;
import me.zhangjh.wx.program.service.pinyin.TblWrongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 2:25 PM 2023/2/23
 * @Description
 */
@Component
public class TblWrongsServiceImpl implements TblWrongsService {

    @Autowired
    private TblWrongsMapper tblWrongsMapper;

    @Override
    public TblWrongs insert(TblWrongs tblWrongs) {
        tblWrongsMapper.insertTblWrongs(tblWrongs);
        return tblWrongs;
    }

    @Override
    public List<TblWrongs> queryByUserId(Long userId) {
        return tblWrongsMapper.queryByUserId(userId);
    }

    @Override
    public boolean deleteById(Long id) {
        TblWrongs tblWrongs = new TblWrongs();
        tblWrongs.setId(id);
        tblWrongs.setIsDeleted(1);
        return tblWrongsMapper.update(tblWrongs) > 0;
    }
}
