package com.final_exam.caferating.form;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class GalleryRegisterForm {

    @NotBlank(message = "Обязательное поле")
    private String photo = "";

    @NotBlank(message = "Обязательное поле")
    private String name = "";


    @NotNull(message = "Обязательное поле")
    private Long personId;


}
