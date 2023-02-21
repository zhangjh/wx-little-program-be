package me.zhangjh.wx.program.service;

import me.zhangjh.wx.program.model.TblDraw;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:52 PM 2023/2/4
 * @Description
 */
public interface TblDrawService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TblDraw queryById(Long id);

    /**
     * 随机返回四条AI作品样例
     * */
    List<TblDraw> queryRandom();

    /**
     * 分页查询
     *
     * @param query 筛选条件
     * @return 查询结果
     */
//    PageDTO<TblDraw> paginQuery(PageQuery<TblDraw> query);

    /**
     * 新增数据
     *
     * @param tblDraw 实例对象
     * @return 实例对象
     */
    TblDraw insert(TblDraw tblDraw);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}
