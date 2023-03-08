package me.zhangjh.wx.program.impl.chatgpt;

import me.zhangjh.wx.program.dto.PageDTO;
import me.zhangjh.wx.program.mapper.chatgpt.TblChatMapper;
import me.zhangjh.wx.program.model.chatgpt.PageChatQuery;
import me.zhangjh.wx.program.model.chatgpt.TblChat;
import me.zhangjh.wx.program.service.chatgpt.TblChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 5:05 PM 2023/2/4
 * @Description
 */
@Component
public class TblChatServiceImpl implements TblChatService {

    @Autowired
    private TblChatMapper tblChatMapper;

    @Override
    public TblChat queryById(Long id) {
        return tblChatMapper.queryById(id);
    }

    @Override
    public PageDTO<TblChat> paginQuery(PageChatQuery query) {
        List<TblChat> tblChats = tblChatMapper.queryByPage(query);
        TblChat tblChat = new TblChat();
        tblChat.setUserId(query.getUserId());
        long total = tblChatMapper.count(tblChat);
        PageDTO<TblChat> pageDTO = new PageDTO<>();
        pageDTO.setData(tblChats);
        pageDTO.setTotal((int) total);
        return pageDTO;
    }

    @Override
    public int count(TblChat tblChat) {
        return (int) tblChatMapper.count(tblChat);
    }

    @Override
    public TblChat insert(TblChat tblChat) {
        tblChatMapper.insert(tblChat);
        return tblChat;
    }

    @Override
    public boolean deleteById(Long id) {
        return tblChatMapper.deleteById(id) > 0;
    }
}
