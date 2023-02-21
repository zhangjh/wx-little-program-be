package me.zhangjh.wx.program.service;

import me.zhangjh.wx.program.model.TblDraw;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 4:26 PM 2023/2/15
 * @Description
 */
public interface TblDefaultDrawService {

    TblDraw insert(TblDraw tblDraw);

    // 固定返回4条随机数据
    List<TblDraw> queryRandom();

    boolean deleteById(Long id);

}