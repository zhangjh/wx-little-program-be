package me.zhangjh.wx.program.impl.pinyin;

import me.zhangjh.wx.program.mapper.pinyin.WxAccountMapper;
import me.zhangjh.wx.program.model.pinyin.TblAccount;
import me.zhangjh.wx.program.service.pinyin.WxAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangjh451@midea.com
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
    public TblAccount queryById(String extId, Integer extType, Integer productType) {
        TblAccount account = new TblAccount();
        account.setExtId(extId);
        account.setExtType(extType);
        account.setProductType(productType);
        return wxAccountMapper.query(account);
    }
}
