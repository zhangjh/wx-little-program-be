package me.zhangjh.wx.program.impl.socket;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.chatgpt.config.HttpSessionWSHelper;
import me.zhangjh.chatgpt.dto.response.ChatResponse;
import me.zhangjh.chatgpt.dto.response.ChatRet;
import me.zhangjh.chatgpt.dto.response.ChatStreamRet;
import me.zhangjh.chatgpt.socket.SocketServer;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public void sendMessage(String userId, String message) {
        // message格式：
        // {
        //  "id":"chatcmpl-6tYOeumTA8x1jMQTx5bI6s97qkrDY",
        //  "object":"chat.completion.chunk",
        //  "created":1678698280,
        //  "model":"gpt-3.5-turbo-0301",
        //  "choices":[{"delta":{"content":" model"},
        //  "index":0,"finish_reason":null}]}
        if(StringUtils.isNotEmpty(message)) {
            log.info("message: {}", message);
            // 结束标记
            if("data: [DONE]".equals(message)) {
                super.sendMessage(userId, message);
                return;
            }
            if(message.startsWith("data:")) {
                String data = message.substring(6);
                ChatResponse chatResponse = JSONObject.parseObject(data, ChatResponse.class);
                List<ChatRet> choices = chatResponse.getChoices();
                for (ChatRet choice : choices) {
                    List<ChatStreamRet> delta = choice.getDelta();
                    for (ChatStreamRet ret : delta) {
                        String content = ret.getContent();
                        super.sendMessage(userId, content);
                    }
                }
            }
        }
    }
}
