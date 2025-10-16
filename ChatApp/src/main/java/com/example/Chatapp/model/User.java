package com.example.Chatapp.model;

import com.example.Chatapp.DTO.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")//user is a reserved word in postgresql so be careful
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullname;
    private String description;

    @Column(unique = true)
    private String username;

    private String password;
    @Column(name = "isExpired")
    private boolean isExpired;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Server> server = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Message> message;

    @OneToMany(mappedBy = "toUser", cascade = CascadeType.ALL)
    private List<Star> starsReceived;

    @OneToMany(mappedBy = "fromUser", cascade = CascadeType.ALL)
    private List<Star> starsGiven;


    public User() {
    }

    public User(String username, String fullname, String password, boolean isExpired, List<Server> server, String description) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.isExpired = isExpired;
        this.server = server;
        this.description = description;
    }

    public User(int id, String fullname, String username, String password, boolean isExpired, List<Server> server, String description) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.isExpired = isExpired;
        this.server = server;
        this.description = description;
    }

    public User(boolean isExpired, String password, String username, String fullname) {
        this.isExpired = isExpired;
        this.password = password;
        this.username = username;
        this.fullname = fullname;
    }

    public User(UserDTO userDTO) {
        this.fullname = userDTO.getFullname();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.isExpired = userDTO.isExpired();
    }

    public double getAverageStars() {
        if(starsGiven.isEmpty()) return 0;

        double total = 0;
        for(Star star : starsReceived){
            total += star.getAmount();
        }

        return total / starsReceived.size();
    }

    public List<Server> getServer() {
        return server;
    }

    public void setServer(List<Server> server) {
        this.server = server;
    } 

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isExpired=" + isExpired +
                '}';
    }
}
