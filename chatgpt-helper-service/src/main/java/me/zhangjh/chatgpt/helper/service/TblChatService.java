package me.zhangjh.chatgpt.helper.service;

import me.zhangjh.chatgpt.helper.dto.PageDTO;
import me.zhangjh.chatgpt.helper.model.PageQuery;
import me.zhangjh.chatgpt.helper.model.TblChat;

/**
 * @author zhangjh451@midea.com
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
    TblChat queryById(String id);
    /**
     * 分页查询
     *
     * @param query 筛选条件
     * @return 查询结果
     */
    PageDTO<TblChat> paginQuery(PageQuery<TblChat> query);
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
    boolean deleteById(String id);
}
