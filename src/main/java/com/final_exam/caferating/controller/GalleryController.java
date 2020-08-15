package com.final_exam.caferating.controller;

import com.final_exam.caferating.form.GalleryRegisterForm;
import com.final_exam.caferating.form.PlaceRegisterForm;
import com.final_exam.caferating.service.GalleryService;
import com.final_exam.caferating.service.PersonService;
import com.final_exam.caferating.service.PlaceService;
import com.final_exam.caferating.service.PropertiesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@AllArgsConstructor
@RequestMapping("/galleries")
@Controller
public class GalleryController {

    private final GalleryService galleryService;
    private final PropertiesService propertiesService;
    private final PersonService personService;

    @GetMapping
    public String getPlaces(Model model, Pageable pageable, Principal principal, HttpServletRequest uriBuilder){
        personService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(galleryService.getAll(pageable), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "gallery";
    }

    @GetMapping("/add-new-photo")
    public String addNewPhoto(Model model, Principal principal){
        personService.checkUserPresence(model, principal);
        return "newPhoto";
    }

    @PostMapping
    public String addNewPhoto(@Valid GalleryRegisterForm galleryRegisterForm, BindingResult validationResult, RedirectAttributes attributes, Principal principal){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/gallery/post";
        }
        galleryService.addNewGallery(galleryRegisterForm, principal);
        return "redirect:/places";
    }




}
