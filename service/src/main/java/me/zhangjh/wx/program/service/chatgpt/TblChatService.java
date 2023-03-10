package me.zhangjh.wx.program.service.chatgpt;

import me.zhangjh.wx.program.dto.PageDTO;
import me.zhangjh.wx.program.model.chatgpt.PageChatQuery;
import me.zhangjh.wx.program.model.chatgpt.TblChat;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:52 PM 2023/2/4
 * @Description
 */
public interface TblChatService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TblChat queryById(Long id);
    /**
     * 分页查询
     *
     * @param query 筛选条件
     * @return 查询结果
     */
    PageDTO<TblChat> paginQuery(PageChatQuery query);

    int count(TblChat tblChat);

    /**
     * 新增数据
     *
     * @param tblChat 实例对象
     * @return 实例对象
     */
    TblChat insert(TblChat tblChat);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}
