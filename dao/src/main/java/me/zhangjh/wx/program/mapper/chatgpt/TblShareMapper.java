package me.zhangjh.wx.program.mapper.chatgpt;

import me.zhangjh.wx.program.model.chatgpt.TblShare;
import org.apache.ibatis.annotations.Mapper;

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
    int deleteById(Long id);
}
