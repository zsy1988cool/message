package com.sane.websocket;

import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class WebSocketMessageHander implements WebSocketHandler {

    private static final Set<WebSocketSession> users = new CopyOnWriteArraySet<>();
    private String userName="";

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        users.add(session);
        userName = (String) session.getAttributes().get("web_user_name");
        System.out.println("第一次连接获取的id--"+userName);
        if (userName != null) {
            // 查询未读消息
            int count = 5;
            session.sendMessage(new TextMessage(count + ""));
        }

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        sendMessageToUser(userName, new TextMessage(webSocketMessage.getPayload() + ""));
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if(webSocketSession.isOpen()) {
            webSocketSession.close();
        }

        users.remove(webSocketSession);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        users.remove(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessageToUsers(TextMessage message) {
        for(WebSocketSession user : users) {
            System.out.println(user.getAttributes().get("web_user_name"));

            try {
                if(user.isOpen()) {
                    user.sendMessage(message);
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给某个用户发送消息，模拟给admin发信息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            System.out.println("从session里面获取的id"+user.getAttributes().get("WEBSOCKET_USERNAME"));
            if (user.getAttributes().get("web_user_name").equals("admin")) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}
