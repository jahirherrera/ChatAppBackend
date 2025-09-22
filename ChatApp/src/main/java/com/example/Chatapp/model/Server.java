package com.example.Chatapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private boolean isPublic;

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "server")
    private List<Chat> chats = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    private List<User> moderators = new ArrayList<>();

    public Server() {
    }

    public Server(String name, User owner, List<Chat> chats, List<User> moderators) {
        this.name = name;
        this.owner = owner;
        this.chats = chats;
        this.moderators = moderators;
    }

    public Server(String name, User owner, List<Chat> chats, boolean isPublic, List<User> moderators) {
        this.name = name;
        this.owner = owner;
        this.chats = chats;
        this.isPublic = isPublic;
        this.moderators = moderators;
    }

    public Server(int id, String name, boolean isPublic, List<Chat> chats, User owner, List<User> moderators) {
        this.id = id;
        this.name = name;
        this.isPublic = isPublic;
        this.chats = chats;
        this.owner = owner;
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }


}
