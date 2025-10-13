package com.example.Chatapp.controller;

import com.example.Chatapp.DTO.ServerDTO;
import com.example.Chatapp.DTO.ServerMemberDTO;
import com.example.Chatapp.model.Server;
import com.example.Chatapp.model.User;
import com.example.Chatapp.service.ServerService;
import com.example.Chatapp.service.UserService;
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
        server.setPublic(serverDTO.isIs_Public());

        List<User> moderators = new ArrayList<>();
        moderators.add(user);

        server.setModerators(moderators);

        serverService.addServer(server);

    }

    @GetMapping("/getAllServer")
    public List<Server> getAllServer(){
        return serverService.getAllServer();
    }

    @DeleteMapping("/deleteServer/{id}")
    public void deleteServerById(@PathVariable("id") int id){
            serverService.deleteServerById(id);
    }

    @GetMapping(path = "/serverOfUser/{username}")
    public List<ServerDTO> getAllServerOfUSer(@PathVariable("username") String username){

        return serverService.getAllServer().stream().filter(s->s.getOwner().getUsername().equals(username) || s.getModerators().stream().anyMatch(m->m.getUsername().equals(username)))
                .map(ServerDTO::new).toList();
    }

    @PostMapping("/addUserToServer")
    public String addUserToServer(@RequestBody ServerMemberDTO serverMemberDTO){
        return serverService.addUserToServer(serverMemberDTO);
    }

    @PostMapping("/deleteUserFromServer")
    public String deleteFromSever(@RequestBody ServerMemberDTO serverMemberDTO){
        return serverService.deleteFromServer(serverMemberDTO);
    }
}

