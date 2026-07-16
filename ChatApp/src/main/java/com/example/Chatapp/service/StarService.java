package com.example.Chatapp.service;

import com.example.Chatapp.DTO.ServerDTO;
import com.example.Chatapp.DTO.StarDTO;
import com.example.Chatapp.model.Star;
import com.example.Chatapp.model.User;
import com.example.Chatapp.repositoty.StarRepo;
import com.example.Chatapp.repositoty.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StarService {

    private final StarRepo starRepo;
    private final UserRepo userRepo;

    public StarService(StarRepo starRepo, UserRepo userRepo) {
        this.starRepo = starRepo;
        this.userRepo = userRepo;
    }

    public Star getStarExisten(String userFrom, String userTo){
        int from_id = userRepo.getUserByUsername(userFrom).getId();
        int to_id = userRepo.getUserByUsername(userTo).getId();

        return starRepo.getStarByUsernames(from_id,to_id);
    }

    public void starsGiven(StarDTO starDTO){
        User userFrom = userRepo.getUserByUsername(starDTO.getUsernameFrom());
        User userTo = userRepo.getUserByUsername(starDTO.getUsernameTo());

        Star star = getStarExisten(userFrom.getUsername(),userTo.getUsername());

        if(star == null){
            star = new Star(starDTO.getAmount(),userFrom,userTo);
            starRepo.save(star);
        } else {
            star.setAmount(starDTO.getAmount());
            starRepo.save(star);
        }

    }

    public List<StarDTO> getStarsFromUser(String username){
        int idUSer = userRepo.getUserByUsername(username).getId();

        return starRepo.getStarsFromUser(idUSer).stream().map(StarDTO::new).toList();
    }
}
