package com.example.websecurity.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages;

    @ManyToOne
    private Server server;

    public Chat() {
    }
    public Chat(String name, Server server) {
        this.name = name;
        this.server = server;
    }

    public Chat(int id, String name, List<Message> messages, Server server) {
        this.id = id;
        this.name = name;
        this.messages = messages;
        this.server = server;
    }

    public Chat(String name, List<Message> messages, Server server) {
        this.name = name;
        this.messages = messages;
        this.server = server;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", messages=" + messages +
                ", server=" + server +
                '}';
    }
}
