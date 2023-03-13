package me.zhangjh.wx.program.impl.socket;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zhangjh451@midea.com
 * @date 8:01 PM 2023/3/12
 * @Description
 */
@Data
@ServerEndpoint(value = "/socket/chatStream")
@Component
@Slf4j
public class ChatSocketServer {

    private Session session;

    private static CopyOnWriteArraySet<ChatSocketServer> webSockets = new CopyOnWriteArraySet<>();
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        log.info("onOpen...");
        webSockets.add(this);
    }

    @OnClose
    public void onClose() {
        log.info("onClose..");
        webSockets.remove(this);
    }

    @OnMessage
    @SneakyThrows
    public void onMessage(String params, Session session) {
        log.info("receiving message..");
        log.info(params);
    }

    @SneakyThrows
    public void sendMessage(String message) {
        for (ChatSocketServer webSocket : webSockets) {
            log.info("send message");
            webSocket.session.getBasicRemote().sendText(message);
        }
    }

    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
    }
}
