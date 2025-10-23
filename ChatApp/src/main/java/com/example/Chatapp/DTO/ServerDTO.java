package com.example.Chatapp.DTO;

import com.example.Chatapp.model.Server;

public class ServerDTO {
    private int id;
    private String name;
    private String ownerUsername;
    private boolean is_Public;
    private int numberModerators;


    public ServerDTO() {
    }



    public ServerDTO(String name, String ownerUsername) {
        this.name = name;
        this.ownerUsername = ownerUsername;
    }

    public ServerDTO(int id, String ownerUsername, String name, boolean is_Public) {
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.name = name;
        this.is_Public = is_Public;
    }

    public ServerDTO(Server server) {
        this.id = server.getId();
        this.ownerUsername = server.getOwner().getUsername();
        this.name = server.getName();
        this.is_Public = server.isPublic();
    }

    public ServerDTO(String name, String ownerUsername, boolean is_Public, int numberModerators) {
        this.name = name;
        this.ownerUsername = ownerUsername;
        this.is_Public = is_Public;
        this.numberModerators = numberModerators;
    }


    public boolean isIs_Public() {
        return is_Public;
    }

    public void setIs_Public(boolean is_Public) {
        this.is_Public = is_Public;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberModerators() {
        return numberModerators;
    }

    public void setNumberModerators(int numberModerators) {
        this.numberModerators = numberModerators;
    }
}
