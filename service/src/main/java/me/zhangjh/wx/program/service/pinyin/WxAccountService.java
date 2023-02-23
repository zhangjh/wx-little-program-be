package me.zhangjh.wx.program.service.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblAccount;

/**
 * @author zhangjh451@midea.com
 * @date 2:08 PM 2023/2/23
 * @Description
 */
public interface WxAccountService {

    TblAccount insert(TblAccount tblAccount);

    TblAccount queryById(String extId, Integer extType, Integer productType);
}
