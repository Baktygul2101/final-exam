package com.final_exam.caferating.form;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class PlaceRegisterForm {

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d]+$", message = "Название должно содержать только буквы : ${validatedValue}")
    private String name = "";

    @NotNull(message = "Обязательное поле")
    private Long galleryId;

}
