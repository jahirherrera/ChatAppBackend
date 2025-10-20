package com.example.Chatapp.repositoty;

import com.example.Chatapp.model.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepo extends JpaRepository<Star, Integer> {
}
