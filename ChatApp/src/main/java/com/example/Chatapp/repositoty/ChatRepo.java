package com.example.Chatapp.repositoty;

import com.example.Chatapp.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<Chat , Integer> {

    Chat findChatById(int id);

    @Query("SELECT c FROM Chat c JOIN c.server s JOIN s.moderators m WHERE m.username = :username")
    List<Chat> getAllChatsByUsername(@Param("username") String username);
}
