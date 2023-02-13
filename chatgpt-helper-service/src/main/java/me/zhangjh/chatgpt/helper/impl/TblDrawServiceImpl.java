package me.zhangjh.chatgpt.helper.impl;

import me.zhangjh.chatgpt.helper.dto.PageDTO;
import me.zhangjh.chatgpt.helper.mapper.TblDrawMapper;
import me.zhangjh.chatgpt.helper.model.PageQuery;
import me.zhangjh.chatgpt.helper.model.TblDraw;
import me.zhangjh.chatgpt.helper.service.TblDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 5:05 PM 2023/2/4
 * @Description
 */
@Component
public class TblDrawServiceImpl implements TblDrawService {

    @Autowired
    private TblDrawMapper tblDrawMapper;

    @Override
    public TblDraw queryById(String id) {
        return tblDrawMapper.queryById(id);
    }

    @Override
    public PageDTO<TblDraw> paginQuery(PageQuery<TblDraw> query) {
        List<TblDraw> tblDraws = tblDrawMapper.queryByPage(query);
        long total = tblDrawMapper.count(query);
        PageDTO<TblDraw> pageDTO = new PageDTO<>();
        pageDTO.setData(tblDraws);
        pageDTO.setTotal((int) total);
        return pageDTO;
    }

    @Override
    public TblDraw insert(TblDraw tblDraw) {
        tblDrawMapper.insert(tblDraw);
        return tblDraw;
    }

    @Override
    public boolean deleteById(String id) {
        return tblDrawMapper.deleteById(id) > 0;
    }
}
