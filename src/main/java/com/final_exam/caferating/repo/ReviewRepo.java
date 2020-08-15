package com.final_exam.caferating.repo;



import com.final_exam.caferating.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    Optional<Review> findByName(String name);

    Page<Review> getAllByPersonEmail(Pageable pageable, String email);
}
