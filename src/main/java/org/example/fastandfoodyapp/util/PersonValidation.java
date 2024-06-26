package org.example.fastandfoodyapp.util;

import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Services.PersonDetailService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidation implements Validator {
    private final PersonDetailService personDetailService;

    public PersonValidation(PersonDetailService personDetailService) {
        this.personDetailService = personDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        try {
            personDetailService.loadUserByUsername(person.getUsername());
            errors.rejectValue("username", "", "Користувач с таким ім'ям вже існує!");
        } catch (UsernameNotFoundException e) {
            return;
        }

        if (personDetailService.findByPhone(person.getPhone()).isEmpty()) {
            errors.rejectValue("phone", "", "Користувач с таким номером вже існує!");
        }

        if (personDetailService.findByEmail(person.getEmail()).isEmpty()) {
            errors.rejectValue("email", "", "Користувач с такою поштою вже існує!");
        }
    }
}
