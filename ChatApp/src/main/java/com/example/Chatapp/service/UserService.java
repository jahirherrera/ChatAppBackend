package com.example.Chatapp.service;

import com.example.Chatapp.DTO.UserDTO;
import com.example.Chatapp.model.Server;
import com.example.Chatapp.model.User;
import com.example.Chatapp.repositoty.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    private final UserRepo userRepo;

    private final ServerService serverService;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder encoder; //we create an object, this is already include in spring security

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepo userRepo, BCryptPasswordEncoder encoder, ServerService serverService){
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.serverService = serverService;
    }

    public User getUserByUsername(String username){
        return userRepo.getUserByUsername(username);
    }

    public String addUser(UserDTO user){
        boolean exists = userRepo.existsByUsernameIgnoreCase(user.getUsername());

        List<Server> serves = serverService.getAllServer().stream().filter(server -> server.getOwner().getUsername().equals("Public")).toList();



        if (exists) {
            return "taken";
        }
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            User userr = new User(user);
            userRepo.save(userr);



            for (String tech : user.getTechnologies()) {
                for (Server s : serves) {
                    if (tech.equalsIgnoreCase(s.getName())) {

                        List<User> moderators = s.getModerators();

                        moderators.add(userr);
                        s.setModerators(moderators);


                        serverService.addServer(s);
                    }
                }
            }



            return "added";
        }catch (Exception e){
            return "Failed to create user";
        }

    }

    public String verify(User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return "failed";
    }

    public UserDTO getUserProfile(String username){
            User user = userRepo.getUserByUsername(username);
            return new UserDTO(user.getUsername(),user.getFullname(),user.getDescription(),user.getAverageStars());
    }


}
