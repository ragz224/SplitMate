package com.example.SplitMate.Repositories;

import com.example.SplitMate.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {
    public Optional<Session> findByTokenAndUser_Id(String token, Long id);
}
