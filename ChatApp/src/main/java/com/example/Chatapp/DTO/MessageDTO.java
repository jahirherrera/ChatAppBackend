package com.example.Chatapp.DTO;

import com.example.Chatapp.model.Message;

public class MessageDTO {
    private int id;
    private String content;
    private int chat_id;
    private int sender_id;
    private String sender_username;
    private String date;

    public MessageDTO() {
    }

    public MessageDTO(int chat_id, String content, int sender_id, String sender_username, String date) {
        this.chat_id = chat_id;
        this.content = content;
        this.sender_id = sender_id;
        this.sender_username = sender_username;
        this.date = date;
    }

    public MessageDTO(int id, String date, String sender_username, int sender_id, int chat_id, String content) {
        this.id = id;
        this.date = date;
        this.sender_username = sender_username;
        this.sender_id = sender_id;
        this.chat_id = chat_id;
        this.content = content;
    }

    public MessageDTO(Message message){
        this.content = message.getContent();
        this.chat_id = message.getChat().getId();
        this.id = message.getId();
        this.sender_id= message.getSender().getId();
        this.sender_username = message.getSender().getUsername();
        this.date = message.getDate();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public String getSender_username() {
        return sender_username;
    }

    public void setSender_username(String sender_username) {
        this.sender_username = sender_username;
    }
}
