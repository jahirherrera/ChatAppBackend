package com.example.Chatapp.controller;

import com.example.Chatapp.DTO.StarDTO;
import com.example.Chatapp.service.StarService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
