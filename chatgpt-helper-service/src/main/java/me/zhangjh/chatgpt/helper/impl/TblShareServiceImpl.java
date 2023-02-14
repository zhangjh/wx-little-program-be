package me.zhangjh.chatgpt.helper.impl;

import me.zhangjh.chatgpt.helper.dto.PageDTO;
import me.zhangjh.chatgpt.helper.mapper.TblShareMapper;
import me.zhangjh.chatgpt.helper.model.PageQuery;
import me.zhangjh.chatgpt.helper.model.TblShare;
import me.zhangjh.chatgpt.helper.service.TblShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 5:06 PM 2023/2/4
 * @Description
 */
@Component
public class TblShareServiceImpl implements TblShareService {

    @Autowired
    private TblShareMapper tblShareMapper;

    @Override
    public TblShare queryById(Long id) {
        return tblShareMapper.queryById(id);
    }

    @Override
    public TblShare queryByQuery(TblShare tblShare) {
        tblShare.setBizStatus(1);
        return tblShareMapper.queryByQuery(tblShare);
    }

    @Override
    public PageDTO<TblShare> paginQuery(PageQuery<TblShare> query) {
        query.getData().setBizStatus(1);
        List<TblShare> tblShares = tblShareMapper.queryByPage(query);
        long total = tblShareMapper.count(query);
        PageDTO<TblShare> pageDTO = new PageDTO<>();
        pageDTO.setData(tblShares);
        pageDTO.setTotal((int) total);
        return pageDTO;
    }

    @Override
    public TblShare insert(TblShare tblShare) {
        tblShareMapper.insert(tblShare);
        return tblShare;
    }

    @Override
    public boolean deleteById(Long id) {
        return tblShareMapper.deleteById(id) > 0;
    }
}
