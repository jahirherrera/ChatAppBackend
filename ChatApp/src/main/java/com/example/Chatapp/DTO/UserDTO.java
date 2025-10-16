package com.example.Chatapp.DTO;

import com.example.Chatapp.model.Message;
import com.example.Chatapp.model.Server;
import com.example.Chatapp.model.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private int id;
    private String fullname;
    private String description;
    private String username;
    private String password;
    private boolean isExpired;
    private List<String> technologies;

    public UserDTO() {
    }

    public UserDTO(String fullname, String username, String password, boolean isExpired) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.isExpired = isExpired;
    }

    public UserDTO(String fullname, String username, String password, boolean isExpired, String description) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.isExpired = isExpired;
        this.description=description;
    }

    public UserDTO(String fullname, String username, String password, boolean isExpired, List<String> techs) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.isExpired = isExpired;
        this.technologies = techs;
    }

    public UserDTO(int id, String fullname, String username, String password, boolean isExpired, List<String> techs) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.isExpired = isExpired;
        this.technologies = techs;
    }

    public UserDTO(User user){
        this.id = user.getId();
        this.fullname = user.getFullname();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isExpired = user.isExpired();
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isExpired=" + isExpired +
                ", techs=" + technologies +
                '}';
    }
}
