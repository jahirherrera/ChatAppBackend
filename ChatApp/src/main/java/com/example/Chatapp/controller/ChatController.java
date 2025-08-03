package com.example.websecurity.controller;

import com.example.websecurity.DTO.ChatDTO;
import com.example.websecurity.model.Chat;
import com.example.websecurity.service.ChatService;
import com.example.websecurity.service.ServerService;
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

    @GetMapping(path = "/chats/{username}")
    public List<ChatDTO> chatByUsername(@PathVariable String username){

        return chatService.getAllChat().stream().filter(c->c.getServer().getModerators().stream().anyMatch(m->m.getUsername().equals(username))).map(ChatDTO::new).toList();

    }
}
