package com.example.Chatapp.repositoty;

import com.example.Chatapp.model.Server;
import com.example.Chatapp.model.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarRepo extends JpaRepository<Star, Integer> {
    // when do a query like this is important to know that we are using the star table therefore we are calling the name in the user model not the user table in the database
    @Query("""
    SELECT s
    FROM Star s
    WHERE s.fromUser.id = :from_id
      AND s.toUser.id = :to_id
    """)
    Star getStarByUsernames(@Param("from_id") int from_id,@Param("to_id") int to_id);

    @Query("""
    SELECT s
    FROM Star s
    WHERE s.toUser.id = :to_id
    """)
    List<Star> getAllStars(@Param("to_id") int to_id);

    @Query("""
    SELECT s
    FROM Star s
    WHERE s.toUser.id = :to_id
    """)
    List<Star> getStarsFromUser(@Param("to_id") int to_id);

}
