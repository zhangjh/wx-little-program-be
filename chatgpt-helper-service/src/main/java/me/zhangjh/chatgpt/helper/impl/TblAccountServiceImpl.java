package me.zhangjh.chatgpt.helper.impl;

import me.zhangjh.chatgpt.helper.mapper.TblAccountMapper;
import me.zhangjh.chatgpt.helper.model.TblAccount;
import me.zhangjh.chatgpt.helper.service.TblAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangjh451@midea.com
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
