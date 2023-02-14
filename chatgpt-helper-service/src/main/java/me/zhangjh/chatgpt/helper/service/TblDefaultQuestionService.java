package me.zhangjh.chatgpt.helper.service;

import me.zhangjh.chatgpt.helper.model.TblQuestion;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 10:08 AM 2023/2/14
 * @Description
 */
public interface TblDefaultQuestionService {

    TblQuestion insert(TblQuestion tblQuestion);

    // 固定返回十条随机数据
    List<TblQuestion> queryRandom();

    boolean deleteById(Long id);
}
