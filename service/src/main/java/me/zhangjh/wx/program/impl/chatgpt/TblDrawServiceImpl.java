package me.zhangjh.wx.program.impl.chatgpt;

import me.zhangjh.wx.program.mapper.chatgpt.TblDrawMapper;
import me.zhangjh.wx.program.model.chatgpt.TblDraw;
import me.zhangjh.wx.program.service.chatgpt.TblDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 5:05 PM 2023/2/4
 * @Description
 */
@Component
public class TblDrawServiceImpl implements TblDrawService {

    @Autowired
    private TblDrawMapper tblDrawMapper;

    @Override
    public TblDraw queryById(Long id) {
        return tblDrawMapper.queryById(id);
    }

    @Override
    public List<TblDraw> queryRandom() {
        return tblDrawMapper.queryRandom();
    }

    @Override
    public int count(TblDraw tblDraw) {
        return tblDrawMapper.count(tblDraw);
    }

//    @Override
//    public PageDTO<TblDraw> paginQuery(PageQuery<TblDraw> query) {
//        List<TblDraw> tblDraws = tblDrawMapper.queryByPage(query);
//        long total = tblDrawMapper.count(query);
//        PageDTO<TblDraw> pageDTO = new PageDTO<>();
//        pageDTO.setData(tblDraws);
//        pageDTO.setTotal((int) total);
//        return pageDTO;
//    }

    @Override
    public TblDraw insert(TblDraw tblDraw) {
        tblDrawMapper.insert(tblDraw);
        return tblDraw;
    }

    @Override
    public boolean deleteById(Long id) {
        return tblDrawMapper.deleteById(id) > 0;
    }
}
