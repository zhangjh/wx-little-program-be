package me.zhangjh.wx.program.impl.pinyin;

import me.zhangjh.wx.program.mapper.pinyin.WxAccountMapper;
import me.zhangjh.wx.program.model.common.TblAccount;
import me.zhangjh.wx.program.service.pinyin.WxAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author njhxzhangjihong@126.com
 * @date 2:13 PM 2023/2/23
 * @Description
 */
@Component
public class WxAccountServiceImpl implements WxAccountService {

    @Autowired
    private WxAccountMapper wxAccountMapper;

    @Override
    public TblAccount insert(TblAccount tblAccount) {
        wxAccountMapper.insertTblAccount(tblAccount);
        return tblAccount;
    }

    @Override
    public TblAccount update(TblAccount tblAccount) {
        wxAccountMapper.update(tblAccount);
        return queryById(tblAccount.getId());
    }

    @Override
    public TblAccount queryById(Long id) {
        return wxAccountMapper.queryById(id);
    }

    @Override
    public TblAccount queryByExtId(String extId, Integer extType) {
        return wxAccountMapper.queryByExtId(extId, extType);
    }
}
