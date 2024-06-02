package com.example.SplitMate.Repositories;

import com.example.SplitMate.Models.Group;
import com.example.SplitMate.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepo extends JpaRepository<Group, Long> {
    Optional<Group> findByName(String Name);
}
