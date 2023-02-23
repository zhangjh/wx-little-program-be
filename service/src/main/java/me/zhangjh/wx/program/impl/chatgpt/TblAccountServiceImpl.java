package me.zhangjh.wx.program.impl.chatgpt;

import me.zhangjh.wx.program.mapper.chatgpt.TblAccountMapper;
import me.zhangjh.wx.program.model.chatgpt.TblAccount;
import me.zhangjh.wx.program.service.chatgpt.TblAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author njhxzhangjihong@126.com
 * @date 5:00 PM 2023/2/4
 * @Description
 */
@Component
public class TblAccountServiceImpl implements TblAccountService {

    @Autowired
    private TblAccountMapper tblAccountMapper;

    @Override
    public TblAccount queryById(String id) {
        return tblAccountMapper.queryById(id);
    }

    @Override
    public TblAccount insert(TblAccount tblAccount) {
        tblAccountMapper.insert(tblAccount);
        return tblAccount;
    }

    @Override
    public TblAccount update(TblAccount tblAccount) {
        tblAccountMapper.update(tblAccount);
        return queryById(tblAccount.getId());
    }

    @Override
    public boolean deleteById(String id) {
        return tblAccountMapper.deleteById(id) > 0;
    }
}
