package me.zhangjh.wx.program.service.chatgpt;

import me.zhangjh.wx.program.model.chatgpt.TblQuestion;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 10:08 AM 2023/2/14
 * @Description
 */
public interface TblDefaultQuestionService {

    TblQuestion insert(TblQuestion tblQuestion);

    // 固定返回十条随机数据
    List<TblQuestion> queryRandom();

    boolean deleteById(Long id);
}
