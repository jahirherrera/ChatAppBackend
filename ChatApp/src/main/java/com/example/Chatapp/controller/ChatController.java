package com.example.Chatapp.controller;

import com.example.Chatapp.DTO.ChatDTO;
import com.example.Chatapp.model.Chat;
import com.example.Chatapp.service.ChatService;
import com.example.Chatapp.service.ServerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    private ChatService chatService;
    private ServerService serverService;


    public ChatController(ChatService chatService,ServerService serverService) {
        this.chatService = chatService;
        this.serverService = serverService;

    }

    @PostMapping("/addChat")
    public void addChat(@RequestBody ChatDTO chat){

        Chat newchat = new Chat(chat.getName(), serverService.getServerById(chat.getServer_id()));

        chatService.addChat(newchat);
    }

    @GetMapping("/getAllchat")
    public List<Chat> getAllChat(){
        return chatService.getAllChat();
    }

    @GetMapping(path = "/chats")
    public List<ChatDTO> chatByUsername(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return chatService.getAllChat().stream().filter(c->c.getServer().getModerators().stream().anyMatch(m->m.getUsername().equals(username))).map(ChatDTO::new).toList();

    }
}
