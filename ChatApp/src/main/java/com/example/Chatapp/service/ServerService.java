package com.example.websecurity.service;

import com.example.websecurity.model.Server;
import com.example.websecurity.repositoty.ServerRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerService {

    private final ServerRepo serverRepo;

    public ServerService(ServerRepo serverRepo) {
        this.serverRepo = serverRepo;
    }

    public void addServer(Server server){
        serverRepo.save(server);
    }

    public List<Server> getAllServer(){
        return serverRepo.findAll();
    }

    public Server getServerById(int id){
        return serverRepo.getServerById(id);
    }
}
