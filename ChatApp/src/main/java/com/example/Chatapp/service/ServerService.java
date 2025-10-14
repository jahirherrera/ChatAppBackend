package com.example.Chatapp.service;

import com.example.Chatapp.DTO.ServerMemberDTO;
import com.example.Chatapp.DTO.UserDTO;
import com.example.Chatapp.model.Server;
import com.example.Chatapp.model.User;
import com.example.Chatapp.repositoty.ServerRepo;
import com.example.Chatapp.repositoty.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServerService {

    private final ServerRepo serverRepo;
    private final UserRepo userRepo;

    public ServerService(ServerRepo serverRepo, UserRepo userRepo) {
        this.serverRepo = serverRepo;
        this.userRepo = userRepo;
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

    public void deleteServerById(int id){
        serverRepo.deleteById(id);
    }

    public List<UserDTO> getAllModerators(int id){
        Server server = serverRepo.getServerById(id);
        return server.getModerators()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    public String addUserToServer(ServerMemberDTO serverMemberDTO){
        if(serverRepo.existsById(serverMemberDTO.getId_server())){
            Server server = serverRepo.getServerById(serverMemberDTO.getId_server());
            if(server.getOwner().getUsername().equals(serverMemberDTO.getOwner())){
                    User newuser = userRepo.getUserByUsername(serverMemberDTO.getUser());
                    List<User> moderators = server.getModerators();

                    moderators.add(newuser);
                    server.setModerators(moderators);

                    serverRepo.save(server);
                    return "User added to the server";

            }else{
                return "You are not the owner of the server";
            }
        }else{
            return "Server not Found";
        }

    }

    public String deleteFromServer(ServerMemberDTO serverMemberDTO){
        if(serverRepo.existsById(serverMemberDTO.getId_server())){
            Server server = serverRepo.getServerById(serverMemberDTO.getId_server());
            if(server.getOwner().getUsername().equals(serverMemberDTO.getOwner())){

                List<User> moderators = new ArrayList<>(server.getModerators().stream().filter(m -> !m.getUsername().equals(serverMemberDTO.getUser())).toList());

                server.setModerators(moderators);

                serverRepo.save(server);
                return "User delete from the server";

            }else{
                return "You are not the owner of the server";
            }
        }else{
            return "Server not Found";
        }
    }

    public void leaveServer(ServerMemberDTO serverMemberDTO){
        Server server = serverRepo.getServerById(serverMemberDTO.getId_server());
        List<User> moderators = new ArrayList<>(server.getModerators().stream().filter(m->!m.getUsername().equals(serverMemberDTO.getUser())).toList());

        server.setModerators(moderators);
        serverRepo.save(server);
    }
}
