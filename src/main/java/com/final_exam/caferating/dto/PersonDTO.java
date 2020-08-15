package com.final_exam.caferating.dto;

import com.final_exam.caferating.model.Person;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonDTO {

    private Long id;
    private String name;
    private String email;
    private String password;

    public static PersonDTO from(Person person) {
        return builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .password(person.getPassword())
                .build();
    }

    public static List<PersonDTO> listFrom(List<Person> objList){
        List<PersonDTO> listDto = new ArrayList<>();
        objList.forEach(obj -> {
            listDto.add(PersonDTO.from(obj));
        });
        return listDto;
    }
}
