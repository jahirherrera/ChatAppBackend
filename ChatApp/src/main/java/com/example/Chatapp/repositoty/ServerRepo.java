package com.example.websecurity.repositoty;

import com.example.websecurity.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepo extends JpaRepository<Server, Integer> {

    Server getServerById(int id);
}
