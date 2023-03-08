package me.zhangjh.wx.program.service.pinyin;

import me.zhangjh.wx.program.model.pinyin.TblAccount;

/**
 * @author njhxzhangjihong@126.com
 * @date 2:08 PM 2023/2/23
 * @Description
 */
public interface WxAccountService {

    TblAccount insert(TblAccount tblAccount);

    TblAccount queryById(String extId, Integer extType, String productType);

    TblAccount queryByExtId(String extId, Integer extType);
}
