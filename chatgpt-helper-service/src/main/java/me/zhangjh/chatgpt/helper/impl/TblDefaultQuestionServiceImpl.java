package me.zhangjh.chatgpt.helper.impl;

import me.zhangjh.chatgpt.helper.mapper.TblDefaultQuestionMapper;
import me.zhangjh.chatgpt.helper.model.TblQuestion;
import me.zhangjh.chatgpt.helper.service.TblDefaultQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 10:34 AM 2023/2/14
 * @Description
 */
@Component
public class TblDefaultQuestionServiceImpl implements TblDefaultQuestionService {

    @Autowired
    private TblDefaultQuestionMapper tblDefaultQuestionMapper;

    @Override
    public TblQuestion insert(TblQuestion tblQuestion) {
        tblDefaultQuestionMapper.insert(tblQuestion);
        return tblQuestion;
    }

    @Override
    public List<TblQuestion> queryRandom() {
        return tblDefaultQuestionMapper.queryRandom();
    }

    @Override
    public boolean deleteById(Long id) {
        return tblDefaultQuestionMapper.deleteById(id) > 0;
    }
}
