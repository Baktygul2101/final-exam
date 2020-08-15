package com.final_exam.caferating.dto;

import com.final_exam.caferating.model.Review;
import lombok.*;
import org.springframework.security.core.parameters.P;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDTO {

    private Long id;
    private String text;
    private PlaceDTO place;
    private int rating;
    private PersonDTO person;

    public static ReviewDTO from(Review review) {
        return builder()
                .id(review.getId())
                .text(review.getText())
                .place(PlaceDTO.from(review.getPlace()))
                .rating(review.getRating())
                .person(PersonDTO.from(review.getPerson()))
                .build();
    }
}
