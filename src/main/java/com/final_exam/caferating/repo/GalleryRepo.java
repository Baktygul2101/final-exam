package com.final_exam.caferating.repo;


import com.final_exam.caferating.model.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalleryRepo extends JpaRepository<Gallery, Long> {

    Optional<Gallery> findByName(String name);
    Page<Gallery> findAll(Pageable pageable);
}
