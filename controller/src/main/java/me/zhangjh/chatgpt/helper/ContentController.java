package me.zhangjh.chatgpt.helper;

import lombok.extern.slf4j.Slf4j;
import me.zhangjh.chatgpt.client.ChatGptService;
import me.zhangjh.chatgpt.dto.request.ImageRequest;
import me.zhangjh.chatgpt.dto.request.TextRequest;
import me.zhangjh.chatgpt.dto.response.ImageResponse;
import me.zhangjh.chatgpt.dto.response.TextResponse;
import me.zhangjh.chatgpt.helper.model.TblChat;
import me.zhangjh.chatgpt.helper.model.TblDraw;
import me.zhangjh.chatgpt.helper.request.ChatRequest;
import me.zhangjh.chatgpt.helper.request.DrawRequest;
import me.zhangjh.chatgpt.helper.service.TblChatService;
import me.zhangjh.chatgpt.helper.service.TblDrawService;
import me.zhangjh.chatgpt.helper.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangjh451@midea.com
 * @date 3:27 PM 2023/2/6
 * @Description 负责和ChatGpt交互生成内容
 */
@RestController
@RequestMapping("/chatHelper")
@CrossOrigin
@Slf4j
public class ContentController {
    @Autowired
    private ChatGptService chatGptService;

    @Autowired
    private TblChatService tblChatService;

    @Autowired
    private TblDrawService tblDrawService;

    @RequestMapping("/chat")
    public Response<String> getChat(ChatRequest chatRequest, HttpServletRequest req) {
        try {
            String userId = req.getHeader("userId");
            chatRequest.headerCheck(req);
            chatRequest.check();

            String question = chatRequest.getQuestion();
            TextRequest textRequest = new TextRequest();
            textRequest.setPrompt("Q:" + question);
            textRequest.setTemperature(0.5);
            TextResponse textCompletion = chatGptService.createTextCompletion(textRequest);
            log.info("Q: {}, A: {}", question, textCompletion);
            if(CollectionUtils.isEmpty(textCompletion.getChoices())
                    || textCompletion.getChoices().get(0).getText().isEmpty()) {
                return new Response<String>().fail("被小主难住了，这个问题我不知道怎么回答---");
            } else {
                String answer = textCompletion.getChoices().get(0).getText();
                // 写入记录
                if(!chatRequest.debugMode(req)) {
                    TblChat tblChat = new TblChat();
                    tblChat.setQuestion(question);
                    tblChat.setAnswer(answer);
                    tblChat.setUserId(userId);
                    tblChatService.insert(tblChat);
                }
                return new Response<String>().success(answer);
            }
        } catch (Throwable t) {
            log.error("getChat exception, ", t);
            return new Response<String>().fail(t.getMessage());
        }
    }

    @RequestMapping("/draw")
    public Response<String> getPicture(DrawRequest drawRequest, HttpServletRequest req) {
        try {
            String userId = req.getHeader("userId");
            drawRequest.headerCheck(req);
            drawRequest.check();

            String term = drawRequest.getTerm();
            ImageRequest imageRequest = new ImageRequest(term);
            ImageResponse imageGeneration = chatGptService.createImageGeneration(imageRequest);
            log.info("term: {}, res: {}", term, imageGeneration);
            if(CollectionUtils.isEmpty(imageGeneration.getData())) {
                return new Response<String>().fail("被小主难住了，这个关键词有点难呀---");
            } else {
                String url = imageGeneration.getData().get(0).getUrl();
                if(!drawRequest.debugMode(req)) {
                    TblDraw tblDraw = new TblDraw();
                    tblDraw.setTerm(term);
                    tblDraw.setUrl(url);
                    tblDraw.setUserId(userId);
                    tblDrawService.insert(tblDraw);
                }
                return new Response<String>().success(url);
            }
        } catch (Throwable t) {
            log.error("getPicture exception, ", t);
            return new Response<String>().fail(t.getMessage());
        }
    }
}
