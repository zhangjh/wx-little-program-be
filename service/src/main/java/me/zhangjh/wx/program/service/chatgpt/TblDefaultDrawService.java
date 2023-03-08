package me.zhangjh.wx.program.service.chatgpt;

import me.zhangjh.wx.program.model.chatgpt.TblDraw;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:26 PM 2023/2/15
 * @Description
 */
public interface TblDefaultDrawService {

    TblDraw insert(TblDraw tblDraw);

    // 固定返回4条随机数据
    List<TblDraw> queryRandom();

    boolean deleteById(Long id);

}
