package com.example.Chatapp.controller;

import com.example.Chatapp.DTO.UserDTO;
import com.example.Chatapp.model.User;
import com.example.Chatapp.service.ServerService;
import com.example.Chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserDTO user){
       return userService.addUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

    @GetMapping("/getUser")
    public User getSingleUser(@RequestBody String username){
        try{
            return userService.getUserByUsername(username);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getUserOptions/{username}")
    public UserDTO getUserOpts(@PathVariable("username") String username){
        return userService.getUserOpts(username);
    }


    @GetMapping("/getUserProfile/{username}")
    public UserDTO getUserProfile(@PathVariable("username") String username ){

        return userService.getUserProfile(username);
    }

    @GetMapping("/hello")
    public String getGreeting(){
        return "Hello and welcome to your homepage";
    }
}
