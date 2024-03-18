package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Model.Enumerables.User_Role;
import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final PersonRepository personRepository;
    @Autowired
    public RegistrationService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void registration(Person person) {
        person.setPerson_role(User_Role.ROLE_CLIENT);
        personRepository.save(person);
    }
}
