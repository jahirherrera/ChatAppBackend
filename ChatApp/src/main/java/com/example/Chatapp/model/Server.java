package com.example.websecurity.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "server")
    private List<Chat> chats = new ArrayList<>();

    @ManyToMany
    private List<User> moderators = new ArrayList<>();

    public Server() {
    }

    public Server(String name, User owner, List<Chat> chats, List<User> moderators) {
        this.name = name;
        this.owner = owner;
        this.chats = chats;
        this.moderators = moderators;
    }

    public Server(int id, String name, User owner, List<Chat> chats, List<User> moderators) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.chats = chats;
        this.moderators = moderators;
    }

    public Server(String name) {
        this.name = name;
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

    public List<Chat> getChats() {
        return chats;
    }

    public void setChats(List<Chat> chats) {
        this.chats = chats;
    }

    public List<User> getModerators() {
        return moderators;
    }

    public void setModerators(List<User> moderators) {
        this.moderators = moderators;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", chats=" + chats +
                ", moderators=" + moderators +
                '}';
    }
}
