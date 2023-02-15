package me.zhangjh.chatgpt.helper;

import me.zhangjh.chatgpt.helper.mapper.TblDrawMapper;
import me.zhangjh.chatgpt.helper.model.TblDraw;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangjh451@midea.com
 * @date 5:11 PM 2023/2/15
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private TblDrawMapper tblDrawMapper;

    @org.junit.Test
    public void test() {
        TblDraw tblDraw = new TblDraw();
        tblDraw.setTerm("test");
        tblDraw.setUrl("testUrl");
        tblDrawMapper.insert(tblDraw);
    }
}
