package me.zhangjh.wx.program.mapper.chatgpt;

import me.zhangjh.wx.program.model.PageQuery;
import me.zhangjh.wx.program.model.chatgpt.TblChat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:41 PM 2023/2/4
 * @Description
 */
@Mapper
public interface TblChatMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TblChat queryById(Long id);
    /**
     * 分页查询指定行数据
     *
     * @param query 查询条件
     * @return 对象列表
     */
    List<TblChat> queryByPage(PageQuery<TblChat> query);

    long count(TblChat tblChat);

    /**
     * 新增数据
     *
     * @param tblChat 实例对象
     * @return 影响行数
     */
    int insert(TblChat tblChat);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
}
