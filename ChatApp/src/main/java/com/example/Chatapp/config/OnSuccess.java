package com.example.Chatapp.config;

import com.example.Chatapp.model.User;
import com.example.Chatapp.repositoty.UserRepo;
import com.example.Chatapp.service.JWTService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OnSuccess implements AuthenticationSuccessHandler {

    @Autowired
    UserRepo userRepo;

    @Autowired
    JWTService jwtService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        if(email==null) return;

        String name = oAuth2User.getAttribute("name");

        String username = email.substring(0,email.indexOf("@"));//pepito@hotmail.comn

        User user = userRepo.findByEmail(email);

        //We need to create an algorithm
        if(user == null){
            userRepo.save(new User(false,"kdsjflkjsd", username, name, email));
        }

        String token = jwtService.generateToken(username);

        Cookie cookie = new Cookie("token", token);

        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        cookie.setAttribute("SameSite", "None");

        response.addCookie(cookie);

        response.sendRedirect("http://localhost:3000/home");
    }
}
