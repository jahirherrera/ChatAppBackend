package com.example.websecurity.DTO;

import com.example.websecurity.model.Chat;

public class ChatDTO {
    private int id;
    private String name;
    private int server_id;

    public ChatDTO() {
    }

    public ChatDTO(String name, int server_id) {
        this.name = name;
        this.server_id = server_id;
    }

    public ChatDTO(int id, String name, int server_id) {
        this.id = id;
        this.name = name;
        this.server_id = server_id;
    }

    public ChatDTO(Chat chat) {
        this.id = chat.getId();
        this.name = chat.getName();
        this.server_id = chat.getServer().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServer_id() {
        return server_id;
    }

    public void setServer_id(int server_id) {
        this.server_id = server_id;
    }
}
