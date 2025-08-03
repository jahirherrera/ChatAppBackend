package com.example.websecurity.service;

import com.example.websecurity.model.Chat;
import com.example.websecurity.repositoty.ChatRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepo chatRepo;

    public ChatService(ChatRepo chatRepo) {
        this.chatRepo = chatRepo;
    }

    public void addChat(Chat chat){
        chatRepo.save(chat);
    }

    public Chat finChatById(int id){
        return chatRepo.findChatById(id);
    }

    public List<Chat> getAllChat(){
        return chatRepo.findAll();
    }
}
