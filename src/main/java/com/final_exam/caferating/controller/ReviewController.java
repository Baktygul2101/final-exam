package com.final_exam.caferating.controller;

import com.final_exam.caferating.form.PlaceRegisterForm;
import com.final_exam.caferating.form.ReviewRegisterForm;
import com.final_exam.caferating.service.PersonService;
import com.final_exam.caferating.service.PlaceService;
import com.final_exam.caferating.service.PropertiesService;
import com.final_exam.caferating.service.ReviewService;
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
@RequestMapping("/reviews")
@Controller
public class ReviewController {

    private final PlaceService placeService;
    private final PropertiesService propertiesService;
    private final PersonService personService;
    private final ReviewService reviewService;


    @GetMapping
    public String getReviews(Model model, Pageable pageable, Principal principal, HttpServletRequest uriBuilder){
        personService.checkUserPresence(model, principal);
        PropertiesService.constructPageable(reviewService.getAllByPersonEmail(pageable, principal), propertiesService.getDefaultPageSize(), model, uriBuilder.getRequestURI());
        return "reviews";
    }

    @GetMapping("/review/{id}")
    public String getReview(@PathVariable Long id, Model model, Principal principal){
        personService.checkUserPresence(model, principal);
        model.addAttribute("review", reviewService.getById(id));
        return "review";
    }

    @GetMapping("/add-review")
    public String addNewPlace(Model model, Principal principal){
        personService.checkUserPresence(model, principal);
        return "review";
    }

    @PostMapping
    public String addNewPlace(@Valid PlaceRegisterForm placeRegisterForm, BindingResult validationResult, RedirectAttributes attributes, Principal principal){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/reviews/add-review";
        }
        placeService.addNewPlace(placeRegisterForm, principal);
        return "redirect:/reviews";
    }



    @GetMapping("/review/{id}/update")
    public String updateReview(@PathVariable Long id, Model model, Principal principal){
        personService.checkUserPresence(model, principal);
        model.addAttribute("review", reviewService.getById(id));
        return "updateReview";
    }

    @PostMapping("/review/{id}/update")
    public String updateReview(@PathVariable Long id, @Valid ReviewRegisterForm reviewRegisterForm, BindingResult validationResult, RedirectAttributes attributes){
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/reviews/review/{id}/update";
        }
        reviewService.updateReviewById(id, reviewRegisterForm);
        return "redirect:/reviews";
    }

}
