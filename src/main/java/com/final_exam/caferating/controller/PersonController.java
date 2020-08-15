package com.final_exam.caferating.controller;

import com.final_exam.caferating.form.PersonRegisterForm;
import com.final_exam.caferating.repo.PersonRepo;
import com.final_exam.caferating.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@AllArgsConstructor
@Controller
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public String mainPage(Model model, Principal principal){

        personService.checkUserPresence(model, principal);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model, Principal principal){

        personService.checkUserPresence(model, principal);
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model, Principal principal){

        personService.checkUserPresence(model, principal);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid PersonRegisterForm personRegisterForm,
                          BindingResult validationResult,
                          RedirectAttributes attributes){
        attributes.addFlashAttribute("dto", personRegisterForm);
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }
        personService.registerPerson(personRegisterForm);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String pageAuthorProfile(Model model, Principal principal) {
        var user = personService.getByEmail(principal.getName());
        model.addAttribute("dto", user);
        return "profile";
    }
}
