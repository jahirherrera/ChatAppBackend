package com.example.websecurity.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @ManyToOne
    private User sender;

    @ManyToOne
    private Chat chat;

    private String date;

    public Message() {
    }

    public Message(User sender, Chat chat, String content, String date) {
        this.sender = sender;
        this.chat = chat;
        this.content = content;
        this.date = date;
    }

    public Message(int id, String content, Chat chat, User sender, String date) {
        this.id = id;
        this.content = content;
        this.chat = chat;
        this.sender = sender;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", sender=" + sender +
                '}';
    }
}
