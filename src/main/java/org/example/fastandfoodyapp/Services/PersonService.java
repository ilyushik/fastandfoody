package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(int id) {
        return personRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }
}
