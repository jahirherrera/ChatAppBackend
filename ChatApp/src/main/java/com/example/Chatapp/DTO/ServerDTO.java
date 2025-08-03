package com.example.websecurity.DTO;

import com.example.websecurity.model.Server;

public class ServerDTO {
    private int id;
    private String name;
    private String ownerUsername;


    public ServerDTO() {
    }


    public ServerDTO(String name, String ownerUsername) {
        this.name = name;
        this.ownerUsername = ownerUsername;
    }

    public ServerDTO(int id, String ownerUsername, String name) {
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.name = name;
    }

    public ServerDTO(Server server) {
        this.id = server.getId();
        this.ownerUsername = server.getOwner().getUsername();
        this.name = server.getName();
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
}
