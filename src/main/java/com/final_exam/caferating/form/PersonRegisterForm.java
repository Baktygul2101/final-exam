package com.final_exam.caferating.form;

import com.final_exam.caferating.exceptions.UniqueEmail;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
public class PersonRegisterForm {

    @NotBlank(message = "Обязательное поле")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "Имя должно содержать только буквы : ${validatedValue}")
    private String name = "";

    @NotBlank(message = "Обязательное поле")
    @Email
    @UniqueEmail
    private String email = "";

    @NotBlank(message = "Обязательное поле")
    @Size(min = 4, message = "Пароль должен содержать минимум 8 символов")
    private String password = "";

}
