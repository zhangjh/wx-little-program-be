package me.zhangjh.wx.program.mapper.chatgpt;

import me.zhangjh.wx.program.model.chatgpt.TblQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 10:09 AM 2023/2/14
 * @Description
 */
public interface TblDefaultQuestionMapper {

    TblQuestion insert(TblQuestion tblQuestion);

    int deleteById(Long id);

    List<TblQuestion> queryRandom();
}
