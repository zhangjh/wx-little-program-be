package me.zhangjh.wx.program.conf;

import me.zhangjh.chatgpt.client.ChatGptService;
import me.zhangjh.chatgpt.service.ChatGptServiceImpl;
import me.zhangjh.chatgpt.socket.ChatSocketServer;
import me.zhangjh.wx.program.mapper.chatgpt.TblApiKeyMapper;
import me.zhangjh.wx.program.model.chatgpt.TblApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author njhxzhangjihong@126.com
 * @date 4:41 PM 2023/2/27
 * @Description
 */
@Configuration
public class BeanConfig {

    @Autowired
    private TblApiKeyMapper tblApiKeyMapper;

    @Bean
    public ChatGptService chatGptService() {
        TblApiKey query = new TblApiKey();
        TblApiKey apiKey = tblApiKeyMapper.query(query);
        ChatGptServiceImpl chatGptService = new ChatGptServiceImpl();
        chatGptService.setApiKey(apiKey.getApiKey());
        return chatGptService;
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public ChatSocketServer chatSocketServer() {
        return new ChatSocketServer();
    }
}
