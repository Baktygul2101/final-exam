package com.final_exam.caferating.exceptions;

import com.final_exam.caferating.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private PersonRepo personRepo;

    public void initialize(UniqueEmail constraint) {
    }

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !personRepo.existsByEmail(email);
    }
}
