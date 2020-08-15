package com.final_exam.caferating.service;

import com.final_exam.caferating.dto.ReviewDTO;
import com.final_exam.caferating.form.ReviewRegisterForm;
import com.final_exam.caferating.model.Review;
import com.final_exam.caferating.repo.PersonRepo;
import com.final_exam.caferating.repo.PlaceRepo;
import com.final_exam.caferating.repo.ReviewRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;

@AllArgsConstructor
@Service
public class ReviewService {

    private final PlaceRepo placeRepo;
    private final PersonRepo personRepo;
    private final ReviewRepo reviewRepo;

    public ReviewDTO getById(Long id){
        return ReviewDTO.from(reviewRepo.findById(id).get());
    }

    public void addReview(ReviewRegisterForm reviewRegisterForm, Principal principal){

        reviewRepo.save(Review.builder()
                .text(reviewRegisterForm.getText())
                .place(placeRepo.findById(reviewRegisterForm.getPlaceId()).get())
                .rating(reviewRegisterForm.getRating())
                .person(personRepo.findByEmail(principal.getName()))
        .build());
    }

    public Page<ReviewDTO> getAllByPersonEmail(Pageable pageable, Principal principal){
        return reviewRepo.getAllByPersonEmail(pageable, principal.getName()).map(ReviewDTO::from);
    }

    public void updateReviewById(Long id, ReviewRegisterForm reviewRegisterForm){
        Review review = reviewRepo.findById(id).get();
        review.setText(reviewRegisterForm.getText());
        review.setRating(reviewRegisterForm.getRating());
        reviewRepo.save(review);
    }

}
