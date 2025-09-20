package com.example.Chatapp.service;

import com.example.Chatapp.model.User;
import com.example.Chatapp.repositoty.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder encoder; //we create an object, this is already include in spring security

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepo userRepo, BCryptPasswordEncoder encoder){
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    public User getUserByUsername(String username){
        return userRepo.getUserByUsername(username);
    }

    public String addUser(User user){
        boolean exists = userRepo.existsByUsernameIgnoreCase(user.getUsername());

        if (exists) {
            return "taken";
        }
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
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


}
