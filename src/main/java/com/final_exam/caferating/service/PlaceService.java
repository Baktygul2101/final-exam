package com.final_exam.caferating.service;

import com.final_exam.caferating.dto.PlaceDTO;
import com.final_exam.caferating.form.PlaceRegisterForm;
import com.final_exam.caferating.model.Place;
import com.final_exam.caferating.repo.GalleryRepo;
import com.final_exam.caferating.repo.PersonRepo;
import com.final_exam.caferating.repo.PlaceRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;

@AllArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepo placeRepo;
    private final PersonRepo personRepo;
    private final GalleryRepo galleryRepo;

    public PlaceDTO getPlaceById(Long id){
        return PlaceDTO.from(placeRepo.findById(id).get());
    }

    public void addNewPlace(PlaceRegisterForm placeRegisterForm, Principal principal){

        placeRepo.save(Place.builder()
        .name(placeRegisterForm.getName())
        .description(placeRegisterForm.getDescription())
        .gallery(galleryRepo.findById(placeRegisterForm.getGalleryId()).get())
        .person(personRepo.findByEmail(principal.getName()))
        .build());
    }

    public Page<PlaceDTO> getAllByPersonEmail(Pageable pageable, Principal principal){
        return placeRepo.getAllByPersonEmail(pageable, principal.getName()).map(PlaceDTO::from);
    }



    public void updatePlaceById(Long id, PlaceRegisterForm placeRegisterForm){
        Place place = placeRepo.findById(id).get();
        place.setName(placeRegisterForm.getName());
        place.setDescription(placeRegisterForm.getDescription());
        placeRepo.save(place);
    }

}
