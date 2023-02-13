package me.zhangjh.chatgpt.helper.mapper;

import me.zhangjh.chatgpt.helper.model.PageQuery;
import me.zhangjh.chatgpt.helper.model.TblShare;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:44 PM 2023/2/4
 * @Description
 */
@Mapper
public interface TblShareMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TblShare queryById(String id);

    /**
     * 通过条件查询单条数据
     * @param tblShare
     * */
    TblShare queryByQuery(TblShare tblShare);

    /**
     * 分页查询指定行数据
     *
     * @param query 查询条件
     * @return 对象列表
     */
    List<TblShare> queryByPage(PageQuery<TblShare> query);

    long count(PageQuery<TblShare> query);

    /**
     * 新增数据
     *
     * @param tblShare 实例对象
     * @return 影响行数
     */
    int insert(TblShare tblShare);

    /**
     * 更新数据
     *
     * @param tblShare 实例对象
     * @return 影响行数
     */
    int update(TblShare tblShare);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);
}
