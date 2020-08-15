package com.final_exam.caferating.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Обязательное поле")
    private String text;


    @ManyToOne @JoinColumn(name= "place_id")
    private Place place;

    private int rating;

    @ManyToOne @JoinColumn(name = "person_id")
    private Person person;
}