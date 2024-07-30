package com.example.repository;

import com.example.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByNameAndTelno(String name, String telno);
}