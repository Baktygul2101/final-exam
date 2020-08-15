package com.final_exam.caferating.dto;

import com.final_exam.caferating.model.Place;
import lombok.*;

@Data
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceDTO {

    private Long id;
    private String name;
    private String description;
    private GalleryDTO gallery;
    private PersonDTO person;

    public static PlaceDTO from(Place place) {
        return builder()
                .id(place.getId())
                .name(place.getName())
                .description(place.getDescription())
                .gallery(GalleryDTO.from(place.getGallery()))
                .person(PersonDTO.from(place.getPerson()))
                .build();
    }
}
