package me.zhangjh.wx.program.mapper.chatgpt;

import me.zhangjh.wx.program.model.chatgpt.TblAccount;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:40 PM 2023/2/4
 * @Description
 */
public interface TblAccountMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TblAccount queryById(String id);

    TblAccount queryByExtId(String extId);

    /**
     * 新增数据
     *
     * @param tblAccount 实例对象
     * @return 影响行数
     */
    int insert(TblAccount tblAccount);


    /**
     * 更新数据
     *
     * @param tblAccount 实例对象
     * @return 影响行数
     */
    int update(TblAccount tblAccount);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);
}
