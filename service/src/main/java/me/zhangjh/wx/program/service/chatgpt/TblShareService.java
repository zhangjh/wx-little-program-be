package me.zhangjh.wx.program.service.chatgpt;

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
