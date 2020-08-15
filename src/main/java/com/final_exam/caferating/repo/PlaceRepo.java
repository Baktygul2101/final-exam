package com.final_exam.caferating.repo;


import com.final_exam.caferating.model.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepo extends JpaRepository<Place, Long> {

    Optional<Place> findByName(String name);

    Page<Place> getAllByPersonEmail(Pageable pageable, String email);
}
