package com.final_exam.caferating.form;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReviewRegisterForm {

    @NotBlank(message = "Обязательное поле")
    private String text = "";

    @NotNull(message = "Обязательное поле")
    private Long placeId;

    private int rating;

}
