package com.example.repository;

import com.example.entity.MissingPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissingPersonRepository  extends JpaRepository<MissingPerson, Long> {
}
