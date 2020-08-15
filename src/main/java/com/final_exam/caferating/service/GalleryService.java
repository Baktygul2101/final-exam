package com.final_exam.caferating.service;


import com.final_exam.caferating.dto.GalleryDTO;
import com.final_exam.caferating.dto.PersonDTO;
import com.final_exam.caferating.form.GalleryRegisterForm;
import com.final_exam.caferating.model.Gallery;
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
public class GalleryService {

    private final GalleryRepo galleryRepo;
    private final PersonRepo personRepo;

    public GalleryDTO getById(Long id){
        return GalleryDTO.from(galleryRepo.findById(id).get());
    }

    public Page<GalleryDTO> getAll(Pageable pageable){
        return galleryRepo.findAll(pageable).map(GalleryDTO::from);
    }

    public void addNewGallery(GalleryRegisterForm galleryRegisterForm, Principal principal){
        galleryRepo.save(Gallery.builder()
        .photo(galleryRegisterForm.getPhoto())
        .name(galleryRegisterForm.getName())
        .build());
    }

       public void updatePlaceById(Long id, GalleryRegisterForm galleryRegisterForm){
           Gallery place = galleryRepo.findById(id).get();
        place.setName(galleryRegisterForm.getName());
        place.setPhoto(galleryRegisterForm.getPhoto());
           galleryRepo.save(place);
    }

}
