package com.final_exam.caferating.service;


import com.final_exam.caferating.dto.PersonDTO;
import com.final_exam.caferating.exceptions.PersonNotFoundException;
import com.final_exam.caferating.form.PersonRegisterForm;
import com.final_exam.caferating.model.Person;
import com.final_exam.caferating.repo.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepo personRepo;
    private final PasswordEncoder encoder;


    public void registerPerson(PersonRegisterForm personRegisterForm){
        var user = Person.builder()
                .name(personRegisterForm.getName())
                .email(personRegisterForm.getEmail())
                .password(encoder.encode(personRegisterForm.getPassword()))
                .build();
        personRepo.save(user);
    }
    public PersonDTO getById(Long id){
        Person person = personRepo.findById(id).get();
        return PersonDTO.from(person);
    }

    public PersonDTO getByEmail(String email) {
        var user = personRepo.findByEmail(email);
              //  .orElseThrow(PersonNotFoundException::new);
        return PersonDTO.from(user);
    }

    public Page<PersonDTO> getAll(Pageable pageable){
        return personRepo.findAll(pageable).map(PersonDTO::from);
    }

    public void checkUserPresence(Model model, Principal principal){
        if(principal != null){
            if (personRepo.existsByEmail(principal.getName())){
                model.addAttribute("person", true);
            }
        }
    }



}