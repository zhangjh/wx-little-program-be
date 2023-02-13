package me.zhangjh.chatgpt.helper.impl;

import me.zhangjh.chatgpt.helper.dto.PageDTO;
import me.zhangjh.chatgpt.helper.mapper.TblChatMapper;
import me.zhangjh.chatgpt.helper.model.PageQuery;
import me.zhangjh.chatgpt.helper.model.TblChat;
import me.zhangjh.chatgpt.helper.service.TblChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 5:05 PM 2023/2/4
 * @Description
 */
@Component
public class TblChatServiceImpl implements TblChatService {

    @Autowired
    private TblChatMapper tblChatMapper;

    @Override
    public TblChat queryById(String id) {
        return tblChatMapper.queryById(id);
    }

    @Override
    public PageDTO<TblChat> paginQuery(PageQuery<TblChat> query) {
        List<TblChat> tblChats = tblChatMapper.queryByPage(query);
        long total = tblChatMapper.count(query.getData());
        PageDTO<TblChat> pageDTO = new PageDTO<>();
        pageDTO.setData(tblChats);
        pageDTO.setTotal((int) total);
        return pageDTO;
    }

    @Override
    public TblChat insert(TblChat tblChat) {
        tblChatMapper.insert(tblChat);
        return tblChat;
    }

    @Override
    public boolean deleteById(String id) {
        return tblChatMapper.deleteById(id) > 0;
    }
}
