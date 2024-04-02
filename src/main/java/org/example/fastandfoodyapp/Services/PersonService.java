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

    @Transactional
    public void editInfo(Person person, int id) {
        Person person1 = findById(id);

        person1.setName(person.getName());
        person1.setSurname(person.getSurname());
        person1.setPhone(person.getPhone());
        person1.setEmail(person.getEmail());
        person1.setUsername(person.getUsername());
        person1.setPerson_password(person.getPerson_password());

        personRepository.save(person1);
    }
}
