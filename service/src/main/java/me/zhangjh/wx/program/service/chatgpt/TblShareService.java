package me.zhangjh.wx.program.service.chatgpt;

import me.zhangjh.wx.program.dto.PageDTO;
import me.zhangjh.wx.program.model.PageQuery;
import me.zhangjh.wx.program.model.chatgpt.TblShare;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:52 PM 2023/2/4
 * @Description
 */
public interface TblShareService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TblShare queryById(Long id);

    /**
     * 通过条件查询单条数据
     * @param tblShare
     * */
    TblShare queryByQuery(TblShare tblShare);

    /**
     * 分页查询
     *
     * @param query 筛选条件
     * @return 查询结果
     */
    PageDTO<TblShare> paginQuery(PageQuery<TblShare> query);
    /**
     * 新增数据
     *
     * @param tblShare 实例对象
     * @return 实例对象
     */
    TblShare insert(TblShare tblShare);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}
