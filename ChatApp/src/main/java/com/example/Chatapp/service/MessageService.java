package com.example.Chatapp.service;


import com.example.Chatapp.DTO.MessageDTO;
import com.example.Chatapp.model.Chat;
import com.example.Chatapp.model.Message;
import com.example.Chatapp.model.User;
import com.example.Chatapp.repositoty.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    UserService userService;

    @Autowired
    ChatService chatService;

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> getAllMessages(){
        return messageRepo.findAll();
    }

    public List<MessageDTO> getAllMessagesChat(int chatId){
        return messageRepo.getAllMessageByChatId(chatId).stream().map(MessageDTO::new).toList();
    }

    public MessageDTO saveMessage(MessageDTO messageDTO){

        User sender = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Chat chat = chatService.finChatById(messageDTO.getChat_id());

        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setSender(sender);
        message.setChat(chat);
        message.setDate(messageDTO.getDate());

        Message saved = messageRepo.save(message);

        return new MessageDTO(saved);
    }
}
