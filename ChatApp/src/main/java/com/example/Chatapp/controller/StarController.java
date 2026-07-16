package com.example.Chatapp.controller;

import com.example.Chatapp.DTO.StarDTO;
import com.example.Chatapp.service.StarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StarController {

    private StarService starService;

    public StarController(StarService starService) {
        this.starService = starService;
    }

    @PostMapping("/giveStars")
    public void starsGiven(@RequestBody  StarDTO starDTO){
        starService.starsGiven(starDTO);
    }

    @GetMapping("/getStars/{username}")
    public List<StarDTO> getStarsFromUser(@PathVariable("username") String username){
        return starService.getStarsFromUser(username);
    }

}
