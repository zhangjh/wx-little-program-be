package me.zhangjh.wx.program.mapper.chatgpt;

import me.zhangjh.wx.program.model.chatgpt.TblApiKey;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangjh451@midea.com
 * @date 4:28 PM 2023/2/27
 * @Description
 */
@Mapper
public interface TblApiKeyMapper {

    TblApiKey insert(TblApiKey tblApiKey);

    TblApiKey query(TblApiKey tblApiKey);
}
