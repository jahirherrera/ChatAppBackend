package com.example.Chatapp.service;

import com.example.Chatapp.DTO.StarDTO;
import com.example.Chatapp.model.Star;
import com.example.Chatapp.model.User;
import com.example.Chatapp.repositoty.StarRepo;
import com.example.Chatapp.repositoty.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class StarService {

    private final StarRepo starRepo;
    private final UserRepo userRepo;

    public StarService(StarRepo starRepo, UserRepo userRepo) {
        this.starRepo = starRepo;
        this.userRepo = userRepo;
    }

    public void starsGiven(StarDTO starDTO){
        User userFrom = userRepo.getUserByUsername(starDTO.getUsernameFrom());
        User userTo = userRepo.getUserByUsername(starDTO.getUsernameTo());

        Star star = new Star(starDTO.getAmount(),userFrom,userTo);

        starRepo.save(star);
    }
}
