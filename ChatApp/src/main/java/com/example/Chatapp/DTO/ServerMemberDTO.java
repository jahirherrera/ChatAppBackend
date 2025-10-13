package com.example.Chatapp.DTO;

public class ServerMemberDTO {
    private int id_server;
    private String owner;
    private String user;

    public ServerMemberDTO() {
    }

    public ServerMemberDTO(int id_server, String user) {
        this.id_server = id_server;
        this.user = user;
    }

    public ServerMemberDTO(int id_server, String owner, String user) {
        this.id_server = id_server;
        this.owner = owner;
        this.user = user;
    }

    public int getId_server() {
        return id_server;
    }

    public void setId_server(int id_server) {
        this.id_server = id_server;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
