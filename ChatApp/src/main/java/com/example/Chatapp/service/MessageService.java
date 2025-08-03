package com.example.websecurity.service;


import com.example.websecurity.model.Message;
import com.example.websecurity.repositoty.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> getAllMessages(){
        return messageRepo.findAll();
    }

    public Message saveMessage(Message message){
        return messageRepo.save(message);
    }
}
