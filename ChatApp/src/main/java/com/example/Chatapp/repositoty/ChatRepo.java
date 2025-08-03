package com.example.websecurity.repositoty;

import com.example.websecurity.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepo extends JpaRepository<Chat , Integer> {

    Chat findChatById(int id);
}
