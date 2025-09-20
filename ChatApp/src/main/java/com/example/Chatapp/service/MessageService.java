package com.example.Chatapp.service;


import com.example.Chatapp.model.Message;
import com.example.Chatapp.repositoty.MessageRepo;
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
