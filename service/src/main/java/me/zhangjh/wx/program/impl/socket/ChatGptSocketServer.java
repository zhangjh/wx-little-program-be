package me.zhangjh.wx.program.impl.socket;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.chatgpt.config.HttpSessionWSHelper;
import me.zhangjh.chatgpt.dto.Message;
import me.zhangjh.chatgpt.dto.request.ChatRequest;
import me.zhangjh.chatgpt.dto.response.ChatResponse;
import me.zhangjh.chatgpt.dto.response.ChatRet;
import me.zhangjh.chatgpt.dto.response.ChatStreamRet;
import me.zhangjh.chatgpt.socket.SocketServer;
import me.zhangjh.wx.program.model.chatgpt.TblChat;
import me.zhangjh.wx.program.service.chatgpt.TblChatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.server.ServerEndpoint;
import java.util.List;

/**
 * @author zhangjh451@midea.com
 * @date 5:26 PM 2023/3/13
 * @Description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ServerEndpoint(value = "/socket/chatStream/{userId}", configurator = HttpSessionWSHelper.class)
@Slf4j
@Component
public class ChatGptSocketServer extends SocketServer {

    private StringBuilder answerCache = new StringBuilder();

    @Autowired
    private TblChatService tblChatService;

    @Override
    public void sendMessage(String userId, String message, String bizContent) {
        log.info("receive message: {}", message);
        // message格式：
        // {
        //  "id":"chatcmpl-6tYOeumTA8x1jMQTx5bI6s97qkrDY",
        //  "object":"chat.completion.chunk",
        //  "created":1678698280,
        //  "model":"gpt-3.5-turbo-0301",
        //  "choices":[{"delta":{"content":" model"},
        //  "index":0,"finish_reason":null}]}
        if(StringUtils.isNotEmpty(message)) {
            // 结束标记
            if("data: [DONE]".equals(message)) {
                super.sendMessage(userId, message, bizContent);
                // 流式响应结果在结束标记这里记录
                record(userId, bizContent);
                // 清空缓存
                answerCache = new StringBuilder();
            }
            if(message.startsWith("data:")) {
                String data = message.substring(6);
                ChatResponse chatResponse = JSONObject.parseObject(data, ChatResponse.class);
                List<ChatRet> choices = chatResponse.getChoices();
                for (ChatRet choice : choices) {
                    List<ChatStreamRet> delta = choice.getDelta();
                    for (ChatStreamRet ret : delta) {
                        String content = ret.getContent();
                        if(StringUtils.isNotEmpty(content)) {
                            answerCache.append(content.trim());
                        }
                        log.info("send message: {}", content);
                        super.sendMessage(userId, content, bizContent);
                    }
                }
            }
        }
    }

    private void record(String userId, String bizContent) {
        ChatRequest chatRequest = JSONObject.parseObject(bizContent, ChatRequest.class);
        TblChat tblChat = new TblChat();
        tblChat.setUserId(userId);
        // messages里最后一个是本次问题
        List<Message> messages = chatRequest.getMessages();
        tblChat.setQuestion(messages.get(messages.size() - 1).getContent());
        tblChat.setAnswer(answerCache.toString());
        tblChatService.insert(tblChat);
    }
}
