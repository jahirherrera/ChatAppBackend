package com.example.Chatapp.service;

import com.example.Chatapp.DTO.ServerDTO;
import com.example.Chatapp.DTO.ServerMemberDTO;
import com.example.Chatapp.DTO.UserDTO;
import com.example.Chatapp.model.Chat;
import com.example.Chatapp.model.Message;
import com.example.Chatapp.model.Server;
import com.example.Chatapp.model.User;
import com.example.Chatapp.repositoty.ChatRepo;
import com.example.Chatapp.repositoty.MessageRepo;
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
    private final ChatRepo chatRepo;
    private final MessageRepo messageRepo;

    public ServerService(ServerRepo serverRepo, UserRepo userRepo, ChatRepo chatRepo, MessageRepo messageRepo) {
        this.serverRepo = serverRepo;
        this.userRepo = userRepo;
        this.chatRepo = chatRepo;
        this.messageRepo = messageRepo;
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
        List<Message> messages = messageRepo.findAll();

        for(Message message : messages){
            if(message.getChat().getServer().getId()==id){
                messageRepo.deleteById(message.getId());
            }
        }

        List<Chat> chats = chatRepo.findAll();

        for(Chat chat : chats){
            if(chat.getServer().getId()==id){
                chatRepo.deleteById(chat.getId());
            }
        }


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

    public ServerDTO getServerInfo(int id){
        try{
            Server server = serverRepo.getServerById(id);

            return new ServerDTO(server.getName(), server.getOwner().getUsername(), server.isPublic(), server.getNumberModerators());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
