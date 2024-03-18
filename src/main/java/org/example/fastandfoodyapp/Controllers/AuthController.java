package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Services.RegistrationService;
import org.example.fastandfoodyapp.util.PersonValidation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidation personValidation;

    public AuthController(RegistrationService registrationService, PersonValidation personValidation) {
        this.registrationService = registrationService;
        this.personValidation = personValidation;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidation.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        registrationService.registration(person);
        return "redirect:/auth/login";
    }
}
