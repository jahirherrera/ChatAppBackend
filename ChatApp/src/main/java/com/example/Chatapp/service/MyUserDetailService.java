package com.example.Chatapp.service;

import com.example.Chatapp.model.MyUserDetails;
import com.example.Chatapp.model.User;
import com.example.Chatapp.repositoty.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService { //implements the interface userdetailsservices

    @Autowired
    private UserRepo userRepo;//repo will get the user data

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //we override the interface's method
        User user = userRepo.findByUsername(username); //we get the user
        if(user == null){
            throw new UsernameNotFoundException("user not found so sad");
        }

        return new MyUserDetails(user);//we return the userdetails from the MyuserDetails the class we created
    }
}
