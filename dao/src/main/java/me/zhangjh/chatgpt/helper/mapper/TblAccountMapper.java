package me.zhangjh.chatgpt.helper.mapper;

import me.zhangjh.chatgpt.helper.model.TblAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhangjh451@midea.com
 * @date 4:40 PM 2023/2/4
 * @Description
 */
@Mapper
public interface TblAccountMapper {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TblAccount queryById(String id);

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
