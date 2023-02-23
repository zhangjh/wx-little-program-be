package me.zhangjh.wx.program;

import lombok.SneakyThrows;
import me.zhangjh.wx.program.mapper.chatgpt.TblDrawMapper;
import me.zhangjh.wx.program.model.chatgpt.TblDraw;
import me.zhangjh.wx.program.service.pinyin.PinyinService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Autowired
    private PinyinService pinyinService;

    @org.junit.Test
    public void test() {
        TblDraw tblDraw = new TblDraw();
        tblDraw.setTerm("test");
        tblDraw.setUrl("testUrl");
        tblDrawMapper.insert(tblDraw);
    }

    @org.junit.Test
    @SneakyThrows
    public void pyTest() {
        List<String> list = pinyinService.transfer("同学你好老师是神仙");
        System.out.println(list);

        System.out.println(pinyinService.transfer("月"));


        System.out.println(pinyinService.transfer("长"));
        System.out.println(pinyinService.transfer("班长"));
        System.out.println(pinyinService.transfer("长短"));
    }
}
