package me.zhangjh.chatgpt.helper.service;

import me.zhangjh.chatgpt.helper.dto.PageDTO;
import me.zhangjh.chatgpt.helper.model.PageQuery;
import me.zhangjh.chatgpt.helper.model.TblDraw;

/**
 * @author zhangjh451@midea.com
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
    TblDraw queryById(String id);
    /**
     * 分页查询
     *
     * @param query 筛选条件
     * @return 查询结果
     */
    PageDTO<TblDraw> paginQuery(PageQuery<TblDraw> query);
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
    boolean deleteById(String id);
}
