package me.zhangjh.wx.program.mapper;

import me.zhangjh.wx.program.model.PageQuery;
import me.zhangjh.wx.program.model.TblDraw;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:28 PM 2023/2/4
 * @Description
 */

@Mapper
public interface TblDrawMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TblDraw queryById(Long id);

    List<TblDraw> queryRandom();

    /**
     * 分页查询指定行数据
     *
     * @param query  查询条件
     * @return 对象列表
     */
//    List<TblDraw> queryByPage(PageQuery<TblDraw> query);

    long count(PageQuery<TblDraw> query);

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
