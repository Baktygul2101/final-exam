package com.final_exam.caferating.repo;


import com.final_exam.caferating.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long> {
    boolean existsByEmail(String email);
    Optional<Person> findByEmail(String email);
    Page<Person> findAll(Pageable pageable);

}
