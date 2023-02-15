package me.zhangjh.chatgpt.helper;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.chatgpt.client.ChatGptService;
import me.zhangjh.chatgpt.dto.request.ImageRequest;
import me.zhangjh.chatgpt.dto.request.TextRequest;
import me.zhangjh.chatgpt.dto.response.BizException;
import me.zhangjh.chatgpt.dto.response.ImageResponse;
import me.zhangjh.chatgpt.dto.response.TextResponse;
import me.zhangjh.chatgpt.helper.model.TblChat;
import me.zhangjh.chatgpt.helper.model.TblDraw;
import me.zhangjh.chatgpt.helper.model.TblQuestion;
import me.zhangjh.chatgpt.helper.request.ChatRequest;
import me.zhangjh.chatgpt.helper.request.DrawRequest;
import me.zhangjh.chatgpt.helper.service.TblChatService;
import me.zhangjh.chatgpt.helper.service.TblDefaultQuestionService;
import me.zhangjh.chatgpt.helper.service.TblDrawService;
import me.zhangjh.chatgpt.helper.vo.Response;
import me.zhangjh.chatgpt.util.HttpClientUtil;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author njhxzhangjihong@126.com
 * @date 3:27 PM 2023/2/6
 * @Description 负责和ChatGpt交互生成内容
 */
@RestController
@RequestMapping("/wx/chatHelper")
@CrossOrigin
@Slf4j
public class ContentController {

    {
        HttpClientUtil.setRequestConfig(RequestConfig.custom()
                .setConnectTimeout(5000)
                // there may be many content returned, set long socket time out
                .setSocketTimeout(10 * 60 * 1000)
                .setConnectionRequestTimeout(5000)
                .build());
    }

    @Autowired
    private ChatGptService chatGptService;

    @Autowired
    private TblChatService tblChatService;

    @Autowired
    private TblDrawService tblDrawService;

    @Autowired
    private TblDefaultQuestionService tblDefaultQuestionService;

    @RequestMapping("/defaultQuestions")
    public Response<List<TblQuestion>> getDefaultQuestions() {
        try {
            return Response.success(tblDefaultQuestionService.queryRandom());
        } catch (Throwable t) {
            log.error("getDefaultQuestions exception, t:", t);
            return Response.fail(t.getMessage());
        }
    }

    @RequestMapping("/defaultDraws")
    public Response<List<TblDraw>> getDefaultDraws() {
        try {
            return Response.success(tblDrawService.queryRandom());
        } catch (Throwable t) {
            log.error("getDefaultDraws exception, t:", t);
            return Response.fail(t.getMessage());
        }
    }

    @RequestMapping("/chat")
    public Response<String> getChat(ChatRequest chatRequest, HttpServletRequest req) {
        try {
            String userId = req.getHeader("userId");
            chatRequest.headerCheck(req);
            chatRequest.check();

            String question = chatRequest.getQuestion();
            TextRequest textRequest = new TextRequest();
            textRequest.setPrompt(question);
            textRequest.setTemperature(0.5);
            TextResponse textCompletion = chatGptService.createTextCompletion(textRequest);
            log.info("Q: {}, A: {}", question, JSONObject.toJSONString(textCompletion));
            if(CollectionUtils.isEmpty(textCompletion.getChoices())
                    || textCompletion.getChoices().get(0).getText().isEmpty()) {
                return Response.fail("ChatGpt服务繁忙，访问超时了...");
            } else {
                String answer = textCompletion.getChoices().get(0).getText().trim();
                // 写入记录
                if(!chatRequest.debugMode(req)) {
                    TblChat tblChat = new TblChat();
                    tblChat.setQuestion(question);
                    tblChat.setAnswer(answer);
                    tblChat.setUserId(userId);
                    tblChatService.insert(tblChat);
                }
                return Response.success(answer);
            }
        } catch (Throwable t) {
            log.error("getChat exception, ", t);
            if(t.getCause() instanceof BizException) {
                return Response.fail(t.getCause().getMessage());
            }
            if(t instanceof RuntimeException) {
                return Response.fail("ChatGpt服务限流，访问被限制了...");
            }
            return Response.fail("未知的服务异常");
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
            log.info("term: {}, res: {}", term, JSONObject.toJSONString(imageGeneration));
            if(CollectionUtils.isEmpty(imageGeneration.getData())) {
                return Response.fail("ChatGpt服务繁忙，访问超时了...");
            } else {
                String url = imageGeneration.getData().get(0).getUrl().trim();
                if(!drawRequest.debugMode(req)) {
                    TblDraw tblDraw = new TblDraw();
                    tblDraw.setTerm(term);
                    tblDraw.setUrl(url);
                    tblDraw.setUserId(userId);
                    tblDrawService.insert(tblDraw);
                }
                return Response.success(url);
            }
        } catch (Throwable t) {
            log.error("getPicture exception, ", t);
            if(t.getCause() instanceof BizException) {
                return Response.fail(t.getCause().getMessage());
            }
            if(t instanceof RuntimeException) {
                return Response.fail("ChatGpt服务限流，访问被限制了...");
            }
            return Response.fail("未知的服务异常");
        }
    }
}
