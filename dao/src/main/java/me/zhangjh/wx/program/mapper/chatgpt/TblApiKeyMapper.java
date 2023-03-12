package me.zhangjh.wx.program.mapper.chatgpt;

import me.zhangjh.wx.program.model.chatgpt.TblApiKey;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:28 PM 2023/2/27
 * @Description
 */
public interface TblApiKeyMapper {

    TblApiKey insert(TblApiKey tblApiKey);

    TblApiKey query(TblApiKey tblApiKey);
}
