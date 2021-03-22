package com.nowwater.board.springwebservice.chatting.controller;

import com.nowwater.board.springwebservice.chatting.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;


@Controller
public class ChatController {

    // message handling methods, send message from one client to the others

    // WebSocketConfig 에서 앞에 /app 붙이도록 라우팅
    @MessageMapping("/chat.sendMessage")  // => "/app/chat.sendMessage"
    @SendTo("/topic/public") // 이 경로를 구독하는 클라이언트에게 전송
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")// => "/app/chat.addUser"
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
