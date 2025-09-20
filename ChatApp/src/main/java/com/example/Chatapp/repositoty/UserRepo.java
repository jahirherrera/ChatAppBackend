package com.example.Chatapp.repositoty;

import com.example.Chatapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username); //we get the method find by username thanks to spring data jpa (create a new query based on naming conventions)

    User getUserByUsername(String username);

    boolean existsByUsernameIgnoreCase(String username);
}
