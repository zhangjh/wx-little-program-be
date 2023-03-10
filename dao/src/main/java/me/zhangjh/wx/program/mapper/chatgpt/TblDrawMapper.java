package me.zhangjh.wx.program.mapper.chatgpt;

import me.zhangjh.wx.program.model.chatgpt.TblDraw;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:28 PM 2023/2/4
 * @Description
 */
public interface TblDrawMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TblDraw queryById(Long id);

    List<TblDraw> queryRandom();

    int count(TblDraw tblDraw);

    /**
     * 新增数据
     *
     * @param tblDraw 实例对象
     * @return 影响行数
     */
    int insert(TblDraw tblDraw);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
}
