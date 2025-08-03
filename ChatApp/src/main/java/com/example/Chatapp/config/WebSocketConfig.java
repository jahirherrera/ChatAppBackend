package com.example.websecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") //url we connect to in this case ws://localhost:8080/ws
                .setAllowedOriginPatterns("http://localhost:3000")
                .withSockJS(); //It makes your WebSocket endpoint more robust and compatible with browsers or networks that do not support WebSocket natively (e.g., due to firewalls, proxies, or older browsers).

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); //route of the room
        registry.setApplicationDestinationPrefixes("/app"); // route for messaging
    }
}
