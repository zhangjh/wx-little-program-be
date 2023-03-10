package me.zhangjh.wx.program.service.pinyin;

import me.zhangjh.wx.program.model.common.TblAccount;

/**
 * @author njhxzhangjihong@126.com
 * @date 2:08 PM 2023/2/23
 * @Description
 */
public interface WxAccountService {

    TblAccount insert(TblAccount tblAccount);

    TblAccount update(TblAccount tblAccount);

    TblAccount queryById(Long id);

    TblAccount queryByExtId(String extId, Integer extType);
}
