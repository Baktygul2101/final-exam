package com.final_exam.caferating.controller;

import com.final_exam.caferating.form.PlaceRegisterForm;
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
@RequestMapping("/places")
@Controller
public class PlaceController {

    private final PlaceService placeService;
    private final PropertiesService propertiesService;
    private final PersonService personService;

    @GetMapping
    public String getPlaces(Model model, Pageable pageable, Principal principal, HttpServletRequest uriBuilder){
        personService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(placeService.getAllByPersonEmail(pageable, principal), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "places";
    }

    @GetMapping("/place/{id}")
    public String getPlace(@PathVariable Long id, Model model, Principal principal){
        personService.checkUserPresence(model, principal);
        model.addAttribute("place", placeService.getPlaceById(id));
        return "place";
    }

    @GetMapping("/add-new-place")
    public String addNewPlace(Model model, Principal principal){
        personService.checkUserPresence(model, principal);
        return "newPlace";
    }

    @PostMapping
    public String addNewPlace(@Valid PlaceRegisterForm placeRegisterForm, BindingResult validationResult, RedirectAttributes attributes, Principal principal){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/places/add-new-place";
        }
        placeService.addNewPlace(placeRegisterForm, principal);
        return "redirect:/places";
    }



    @GetMapping("/place/{id}/update")
    public String updatePlace(@PathVariable Long id, Model model, Principal principal){
        personService.checkUserPresence(model, principal);
        model.addAttribute("task", placeService.getPlaceById(id));
        return "updatePlace";
    }

    @PostMapping("/place/{id}/update")
    public String updatePlace(@PathVariable Long id, @Valid PlaceRegisterForm placeRegisterForm, BindingResult validationResult, RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/places/place/{id}/update";
        }
        placeService.updatePlaceById(id, placeRegisterForm);
        return "redirect:/places";
    }

}
