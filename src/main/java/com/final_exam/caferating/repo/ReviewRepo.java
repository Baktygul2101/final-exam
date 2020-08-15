package com.final_exam.caferating.repo;



import com.final_exam.caferating.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepo extends JpaRepository<Review, Long> {

    Page<Review> getAllByPersonEmail(Pageable pageable, String email);
}
