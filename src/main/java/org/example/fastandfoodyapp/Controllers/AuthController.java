package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Mails.MailService;
import org.example.fastandfoodyapp.Mails.MailStructure;
import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Services.RegistrationService;
import org.example.fastandfoodyapp.util.PersonValidation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final MailService mailService;
    private final RegistrationService registrationService;
    private final PersonValidation personValidation;

    public AuthController(MailService mailService, RegistrationService registrationService, PersonValidation personValidation) {
        this.mailService = mailService;
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

    // registration with uploading image
//    @PostMapping("/registration")
//    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
//                                      @RequestParam("file") MultipartFile file) throws IOException {
//        personValidation.validate(person, bindingResult);
//        if(bindingResult.hasErrors()) {
//            return "/auth/registration";
//        }
//        registrationService.registration(person, file);
//        MailStructure mail = new MailStructure("Реєстрація успішна", person.getName() +
//                ", вітаємо Вас у нашому ресторані");
//        mailService.sendMail(person.getEmail(), mail);
//        return "redirect:/auth/login";
//    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidation.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        registrationService.registration(person);
        MailStructure mail = new MailStructure("Реєстрація успішна", person.getName() +
                ", вітаємо Вас у нашому ресторані");
        mailService.sendMail(person.getEmail(), mail);
        return "redirect:/auth/login";
    }
}
