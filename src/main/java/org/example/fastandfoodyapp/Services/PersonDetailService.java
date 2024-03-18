package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Repositories.PersonRepository;
import org.example.fastandfoodyapp.Security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {
    private final PersonRepository personRepository;

    public PersonDetailService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByUsername(username);
        if(person.isEmpty()) {
            throw new UsernameNotFoundException("Користувача не знайдено");
        }
        return new PersonDetails(person.get());
    }
}
