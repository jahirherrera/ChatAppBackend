package com.example.Chatapp.repositoty;

import com.example.Chatapp.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerRepo extends JpaRepository<Server, Integer> {

    @Query("""
    SELECT s
    FROM Server s
    LEFT JOIN s.moderators mCount
    WHERE s NOT IN (
        SELECT s2 FROM Server s2 JOIN s2.moderators u WHERE u.username = :username
    )
    GROUP BY s
    ORDER BY COUNT(mCount) DESC
    """)
    List<Server> getAllServersNoUser(@Param("username") String username);

    @Query("""
    SELECT s
    FROM Server s
    LEFT JOIN s.moderators mCount
    WHERE s NOT IN (
        SELECT s2 FROM Server s2 JOIN s2.moderators u WHERE u.username = :username
    )
    AND LOWER(s.name) LIKE LOWER(CONCAT('%', :input, '%'))
    GROUP BY s
    ORDER BY COUNT(mCount) DESC
    """)
    List<Server> getAllServersNoUserWith(@Param("username") String username,@Param("input") String input);

    Server getServerById(int id);
}
