package me.zhangjh.wx.program.impl.chatgpt;

import me.zhangjh.wx.program.mapper.chatgpt.TblDefaultQuestionMapper;
import me.zhangjh.wx.program.model.chatgpt.TblQuestion;
import me.zhangjh.wx.program.service.chatgpt.TblDefaultQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author njhxzhangjihong@126.com
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
