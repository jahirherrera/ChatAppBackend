package com.example.Chatapp.controller;

import com.example.Chatapp.DTO.MessageDTO;
import com.example.Chatapp.model.Chat;
import com.example.Chatapp.model.Message;
import com.example.Chatapp.model.User;
import com.example.Chatapp.service.ChatService;
import com.example.Chatapp.service.MessageService;
import com.example.Chatapp.service.UserService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;
    private final ChatService chatService;
    private final MessageService messageService;

    public WebSocketController(SimpMessagingTemplate messagingTemplate, UserService userService, ChatService chatService, MessageService messageService) {
        this.messagingTemplate = messagingTemplate;
        this.userService = userService;
        this.chatService = chatService;
        this.messageService = messageService;
    }

    @MessageMapping("/server/{serverId}/chat/{chatId}")
    public void sendMessage(
            @DestinationVariable int serverId,
            @DestinationVariable int chatId,
            @Payload MessageDTO m
    ){

        String destination = "/topic/server/" + serverId + "/chat/"+chatId;


        messagingTemplate.convertAndSend(destination,m);
    }
}
