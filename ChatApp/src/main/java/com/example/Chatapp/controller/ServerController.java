package com.example.websecurity.controller;

import com.example.websecurity.DTO.ServerDTO;
import com.example.websecurity.model.Server;
import com.example.websecurity.model.User;
import com.example.websecurity.service.ServerService;
import com.example.websecurity.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServerController {

    private ServerService serverService;
    private UserService userService;


    public ServerController(ServerService serverService, UserService userService) {
        this.serverService = serverService;
        this.userService = userService;
    }

    @PostMapping("/addServer")
    public void addServer(@RequestBody ServerDTO serverDTO){

        User user = userService.getUserByUsername(
                serverDTO.getOwnerUsername()
        );

        Server server = new Server();
        server.setName(serverDTO.getName());
        server.setOwner(user);

        List<User> moderators = new ArrayList<>();
        moderators.add(user);

        server.setModerators(moderators);

        serverService.addServer(server);

    }

    @GetMapping("/getAllServer")
    public List<Server> getAllServer(){
        return serverService.getAllServer();
    }

    @GetMapping(path = "/serverOfUser/{username}")
    public List<ServerDTO> getAllServerOfUSer(@PathVariable("username") String username){

        return serverService.getAllServer().stream().filter(s->s.getOwner().getUsername().equals(username) || s.getModerators().stream().anyMatch(m->m.getUsername().equals(username)))
                .map(ServerDTO::new).toList();
    }
}

