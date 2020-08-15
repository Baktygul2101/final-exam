package com.final_exam.caferating.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String name;

    @Email
    @NotBlank(message = "Обязательное поле")
    @Column(length = 64)
    private String email;

    @NotBlank(message = "Обязательное поле")
    @Size(min = 4, message = "Пароль должен содержать минимум 4 символов")
    @Column(length = 64)
    private String password;

    @Column
    @Builder.Default
    private boolean enabled = true;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    @Builder.Default
    private String role = "USER";

}
