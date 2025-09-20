package com.example.Chatapp.controller;

import com.example.Chatapp.DTO.MessageDTO;
import com.example.Chatapp.model.Chat;
import com.example.Chatapp.model.Message;
import com.example.Chatapp.model.User;
import com.example.Chatapp.service.ChatService;
import com.example.Chatapp.service.MessageService;
import com.example.Chatapp.service.UserService;
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

        User sender = userService.getUserByUsername(m.getSender_username());
        Chat chat = chatService.finChatById(m.getChat_id());

        Message message = new Message();
        message.setContent(m.getContent());
        message.setSender(sender);
        message.setChat(chat);
        message.setDate(m.getDate());

        Message saved = messageService.saveMessage(message);

        return new MessageDTO(saved);
    }

    @GetMapping(path = "/messages/{chatid}")
    public List<MessageDTO> getAllMessageChat(@PathVariable int chatid){

        return messageService.getAllMessages().stream().filter(m -> m.getChat().getId() == chatid).map(MessageDTO::new).toList();
    }

}
