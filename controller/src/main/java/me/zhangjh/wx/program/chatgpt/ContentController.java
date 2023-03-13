package me.zhangjh.wx.program.chatgpt;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.chatgpt.client.ChatGptService;
import me.zhangjh.chatgpt.constant.RoleEnum;
import me.zhangjh.chatgpt.dto.Message;
import me.zhangjh.chatgpt.dto.request.ImageRequest;
import me.zhangjh.chatgpt.dto.request.TextRequest;
import me.zhangjh.chatgpt.dto.response.BizException;
import me.zhangjh.chatgpt.dto.response.ChatResponse;
import me.zhangjh.chatgpt.dto.response.ImageResponse;
import me.zhangjh.chatgpt.dto.response.TextResponse;
import me.zhangjh.share.response.Response;
import me.zhangjh.share.util.HttpClientUtil;
import me.zhangjh.wx.program.chatgpt.request.ChatRequest;
import me.zhangjh.wx.program.chatgpt.request.DrawRequest;
import me.zhangjh.wx.program.impl.socket.ChatSocketServer;
import me.zhangjh.wx.program.model.chatgpt.TblChat;
import me.zhangjh.wx.program.model.chatgpt.TblDraw;
import me.zhangjh.wx.program.model.chatgpt.TblQuestion;
import me.zhangjh.wx.program.service.chatgpt.TblChatService;
import me.zhangjh.wx.program.service.chatgpt.TblDrawService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.Executors;

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
                .setConnectTimeout(50000)
                // there may be many content returned, set long socket time out
                .setSocketTimeout(10 * 60 * 1000)
                .setConnectionRequestTimeout(5000)
                .build());

        List<String> questions = Arrays.asList("找对象为什么这么难？",
                "俄乌冲突什么时候能结束？",
                "怎么看待詹姆斯获得NBA得分王？",
                "给我来一点Java八股文？",
                "给我来几个富有诗意的男孩名字？",
                "给我来首中晚唐的诗歌？",
                "以和谐为主题帮我写一段讲话稿？",
                "什么是斐波拉契数列？",
                "给我讲几个笑话？",
                "一句话总结一下《甄嬛传》说了什么？",
                "推荐几部好看的电影？",
                "推荐几本值得一看的书？",
                "如何制作精美的PPT？",
                "架构师应该掌握什么样的技能？",
                "如何评价马斯克收购推特的行为？",
                "普通人如何应对通货膨胀？",
                "有哪些普通人可以进行的投资理财方式？",
                "推荐一些中国值得旅游的景点？",
                "推荐一些欧洲值得旅游的景点？",
                "周杰伦有哪些专辑？",
                "怎样学习人工智能？",
                "如何计算笛卡尔积？",
                "如何阅读一本书？",
                "如何快速学习英语？",
                "人工智能会让人类失业吗？",
                "A股为什么总在3000点徘徊？");
        for (String question : questions) {
            TblQuestion tblQuestion = new TblQuestion();
            tblQuestion.setQuestion(question);
            defaultQuestions.add(tblQuestion);
        }

        Map<String, String> draws = new HashMap<>();
        draws.put("海边散步的情侣 动漫风格 高清画质", "https://wx2.sinaimg.cn/bmiddle/62d95157ly1hb4gl6pkfkj20sg0sg1kx.jpg");
        draws.put("给流浪地球制作一款精美的科幻风格海报", "https://wx2.sinaimg.cn/bmiddle/62d95157ly1hb4h8cwsjgj20sg0sgb29.jpg");
        draws.put("一只火红的狐狸在吃紫色的葡萄，油画风格", "https://wx4.sinaimg.cn/bmiddle/62d95157ly1hb4go2zg0qj20sg0sge81.jpg");
        draws.put("玻璃房子里的雨林，郁郁葱葱的树木，藤蔓缠绕在架子上，格雷格·鲁特科夫斯基、詹姆斯·格尼和托马斯·金卡德", "https://wx4.sinaimg.cn/bmiddle/62d95157ly1hb4h32kokjj20sg0sgkjl.jpg");
        draws.put("轨道空间站，科学和技术，Jonn Berkey，高度详细", "https://wx3.sinaimg.cn/bmiddle/62d95157ly1hb4h365x97j20sg0sghdt.jpg");
        draws.put("云上的伟大的中国宫殿，早晨，远处瀑布流下的山，幻想，高清，超广角", "https://wx2.sinaimg.cn/bmiddle/62d95157ly1hb4h3ait7hj20sg0sg7wh.jpg");
        draws.put("来一张中国水墨画风格的素描，内容是一名渔夫在船上垂钓", "https://wx1.sinaimg.cn/bmiddle/62d95157ly1hb4h3cikthj20sg0sg4qp.jpg");
        draws.put("可爱的黄色小猫咪", "https://wx1.sinaimg.cn/bmiddle/62d95157ly1hb4hushdpej20sg0sg4qp.jpg");
        draws.put("凶猛的老虎 丛林 高清", "https://wx2.sinaimg.cn/bmiddle/62d95157ly1hb5ea25appj20sg0sg4qp.jpg");
        draws.put("吃竹子的熊猫", "https://wx4.sinaimg.cn/bmiddle/62d95157ly1hb5ea6ldb3j20sg0sgb29.jpg");
        draws.put("亚马孙雨林", "https://wx1.sinaimg.cn/bmiddle/62d95157ly1hb5eed9e58j20sg0sgqv5.jpg");
        draws.put("雷雨天 闪电的天空", "https://wx4.sinaimg.cn/bmiddle/62d95157ly1hb5eeb1ecpj20sg0sg4q6.jpg");
        draws.put("小熊猫", "https://wx1.sinaimg.cn/bmiddle/62d95157ly1hb5erbfyqoj20sg0sghdt.jpg");

        for (Map.Entry<String, String> entry : draws.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            TblDraw tblDraw = new TblDraw();
            tblDraw.setTerm(key);
            tblDraw.setUrl(value);
            tblDraw.setUserId("-1");
            defaultDraws.add(tblDraw);
        }
    }

    public static List<TblQuestion> defaultQuestions = new ArrayList<>();

    public static List<TblDraw> defaultDraws = new ArrayList<>();

    @Autowired
    private ChatGptService chatGptService;

    @Autowired
    private TblChatService tblChatService;

    @Autowired
    private TblDrawService tblDrawService;

    @Autowired
    private ChatSocketServer chatSocketServer;

    @RequestMapping("/defaultQuestions")
    public Response<List<TblQuestion>> getDefaultQuestions() {
        try {
            List<TblQuestion> res = new ArrayList<>();
            Collections.shuffle(defaultQuestions);
            for (int i = 0; i < 10; i++) {
                res.add(defaultQuestions.get(i));
            }
            return Response.success(res);
        } catch (Throwable t) {
            log.error("getDefaultQuestions exception, t:", t);
            return Response.fail(t.getMessage());
        }
    }

    @RequestMapping("/defaultDraws")
    public Response<List<TblDraw>> getDefaultDraws() {
        try {
            List<TblDraw> res = new ArrayList<>();
            Collections.shuffle(defaultDraws);
            for (int i = 0; i < 4; i++) {
                res.add(defaultDraws.get(i));
            }
            return Response.success(res);
        } catch (Throwable t) {
            log.error("getDefaultDraws exception, t:", t);
            return Response.fail(t.getMessage());
        }
    }

    @PostMapping("/chat")
    public Response<String> getChat(@RequestBody ChatRequest chatRequest, HttpServletRequest req) {
        try {
            String userId = req.getHeader("userId");
            chatRequest.headerCheck(req);
            chatRequest.check();

            String question = URLDecoder.decode(chatRequest.getQuestion(), Charset.defaultCharset());
            TextRequest textRequest = new TextRequest();
            textRequest.setPrompt(question);
            textRequest.setTemperature(0.5);
            textRequest.setMaxTokens(2048);
            TextResponse textCompletion = chatGptService.createTextCompletion(textRequest);
            log.info("Q: {}, A: {}", question, JSONObject.toJSONString(textCompletion));
            if(CollectionUtils.isEmpty(textCompletion.getChoices())
                    || textCompletion.getChoices().get(0).getText().isEmpty()) {
                return Response.fail("ChatGpt服务繁忙，访问超时了...");
            } else {
                String answer = textCompletion.getChoices().get(0).getText().trim();
                // 写入记录
                if(!chatRequest.debugMode(req)) {
                    recordChat(userId, question, answer);
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

    @PostMapping("/newChat")
    public Response<String> getNewChat(@RequestBody ChatRequest chatRequest, HttpServletRequest req) {
        String userId = req.getHeader("userId");
        chatRequest.headerCheck(req);
        chatRequest.check();

        // 构建本次提问上下文
        List<Message> messages = new ArrayList<>();

        String question = URLDecoder.decode(chatRequest.getQuestion(), Charset.defaultCharset());
        Map<String, String> contextMap = chatRequest.getContextMap();
        if(MapUtils.isNotEmpty(contextMap)) {
            for (Map.Entry<String, String> entry : contextMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Message q = new Message();
                q.setRole(RoleEnum.user.name());
                q.setContent(key);
                messages.add(q);
                Message answer = new Message();
                answer.setRole(RoleEnum.assistant.name());
                answer.setContent(value);
                messages.add(answer);
            }
        }
        me.zhangjh.chatgpt.dto.request.ChatRequest request = new me.zhangjh.chatgpt.dto.request.ChatRequest();
        Message message = new Message();
        message.setRole(RoleEnum.user.name());
        message.setContent(question);
        messages.add(message);
        request.setMessages(messages);

        ChatResponse chatCompletion = chatGptService.createChatCompletion(request);
        String answer = chatCompletion.getChoices().get(0).getMessage().getContent().trim();
        // 记录
        if(!chatRequest.debugMode(req)) {
            recordChat(userId, question, answer);
        }
        return Response.success(answer);
    }

    @PostMapping("/chatStream")
    public SseEmitter getChatStream(@RequestBody ChatRequest chatRequest, HttpServletRequest req) {
        String userId = req.getHeader("userId");
        Assert.isTrue(StringUtils.isNotEmpty(userId), "userId为空");
        chatRequest.headerCheck(req);
        chatRequest.check();
        // 构建本次提问上下文
        List<Message> messages = new ArrayList<>();

        String question = URLDecoder.decode(chatRequest.getQuestion(), Charset.defaultCharset());
        Map<String, String> contextMap = chatRequest.getContextMap();
        if(MapUtils.isNotEmpty(contextMap)) {
            for (Map.Entry<String, String> entry : contextMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Message q = new Message();
                q.setRole(RoleEnum.user.name());
                q.setContent(key);
                messages.add(q);
                Message answer = new Message();
                answer.setRole(RoleEnum.assistant.name());
                answer.setContent(value);
                messages.add(answer);
            }
        }
        me.zhangjh.chatgpt.dto.request.ChatRequest request = new me.zhangjh.chatgpt.dto.request.ChatRequest();
        Message message = new Message();
        message.setRole(RoleEnum.user.name());
        message.setContent(question);
        messages.add(message);
        request.setMessages(messages);
        request.setConsumerCb(chatSocketServer::sendMessage);
        return chatGptService.createChatCompletionStream(request);
    }

    @GetMapping("/draw")
    public Response<String> getPicture(DrawRequest drawRequest, HttpServletRequest req) {
        try {
            String userId = req.getHeader("userId");
            drawRequest.headerCheck(req);
            drawRequest.check();

            String term = URLDecoder.decode(drawRequest.getTerm(), Charset.defaultCharset());
            ImageRequest imageRequest = new ImageRequest(URLDecoder.decode(term, Charset.defaultCharset()));
            imageRequest.setSize("256x256");
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

    @GetMapping("/newDraw")
    public Response<String> getNewDraw(DrawRequest drawRequest, HttpServletRequest req) {
        return Response.success("");
    }

    @GetMapping("/getTips")
    public Response<String> getTips() {
        String tips = "已接入最新ChatGpt模型，智能升级，效果提升显著！！接口响应时间在服务高峰期间不可控，可能需要较长等待，如超时重试或更换时段~~";
        return Response.success(tips);
    }

    @GetMapping("/sse")
    public SseEmitter getSse() {
        final SseEmitter emitter = new SseEmitter(0L);

        Executors.newFixedThreadPool(1).execute(() -> {
            try {
                for(int i=0;i < 100;i++){
                    emitter.send("hello" + i);
                    Thread.sleep(1000);
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    private void recordChat(String userId, String question, String answer) {
        TblChat tblChat = new TblChat();
        tblChat.setQuestion(question);
        tblChat.setAnswer(answer);
        tblChat.setUserId(userId);
        tblChatService.insert(tblChat);
    }
}
