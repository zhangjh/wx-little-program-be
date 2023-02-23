package me.zhangjh.wx.program.mapper.chatgpt;

import me.zhangjh.wx.program.model.chatgpt.TblQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 10:09 AM 2023/2/14
 * @Description
 */
@Mapper
public interface TblDefaultQuestionMapper {

    TblQuestion insert(TblQuestion tblQuestion);

    int deleteById(Long id);

    List<TblQuestion> queryRandom();
}
