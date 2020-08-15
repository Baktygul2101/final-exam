package com.final_exam.caferating.dto;


import com.final_exam.caferating.model.Gallery;
import lombok.*;


@Data
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GalleryDTO {

    private Long id;
    private String photo;

    public static GalleryDTO from(Gallery position) {
        return builder()
                .id(position.getId())
                .photo(position.getPhoto())
                .build();
    }
}
