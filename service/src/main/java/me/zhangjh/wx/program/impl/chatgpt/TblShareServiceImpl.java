package me.zhangjh.wx.program.impl.chatgpt;

import me.zhangjh.wx.program.mapper.chatgpt.TblShareMapper;
import me.zhangjh.wx.program.model.chatgpt.TblShare;
import me.zhangjh.wx.program.service.chatgpt.TblShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public TblShare insert(TblShare tblShare) {
        tblShareMapper.insert(tblShare);
        return tblShare;
    }

    @Override
    public boolean deleteById(Long id) {
        return tblShareMapper.deleteById(id) > 0;
    }
}
