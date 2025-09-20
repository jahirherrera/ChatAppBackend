package com.example.Chatapp.Filter;

import com.example.Chatapp.model.MyUserDetails;
import com.example.Chatapp.service.JWTService;
import com.example.Chatapp.service.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String autHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;





        try {
            if (autHeader != null && autHeader.startsWith("Bearer ")) {
                token = autHeader.substring(7);
                username = jwtService.extractUsername(token);
            }
        }catch (Exception e) {
            System.out.println("Invalid token: " + e.getMessage());
        }



        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = applicationContext.getBean(MyUserDetailService.class).loadUserByUsername(username);
            
            if(jwtService.validateToken(token, userDetails)){
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

               usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            
        }

        filterChain.doFilter(request, response);
    }
}
