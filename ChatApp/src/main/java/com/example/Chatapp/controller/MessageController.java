package com.example.Chatapp.controller;

import com.example.Chatapp.DTO.MessageDTO;
import com.example.Chatapp.model.Chat;
import com.example.Chatapp.model.Message;
import com.example.Chatapp.model.User;
import com.example.Chatapp.service.ChatService;
import com.example.Chatapp.service.MessageService;
import com.example.Chatapp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final ChatService chatService;

    public MessageController(MessageService messageService, UserService userService,ChatService chatService) {
        this.messageService = messageService;
        this.userService = userService;
        this.chatService = chatService;
    }

    @PostMapping("/saveMessage")
    public MessageDTO saveMessage(@RequestBody MessageDTO m){

        return messageService.saveMessage(m);
    }

    @GetMapping(path = "/messages/{chatid}")
    public List<MessageDTO> getAllMessageChat(@PathVariable int chatid){

        return messageService.getAllMessagesChat(chatid);
    }

}
