package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Mails.MailService;
import org.example.fastandfoodyapp.Mails.MailStructure;
import org.example.fastandfoodyapp.Model.Enumerables.User_Role;
import org.example.fastandfoodyapp.Model.PasswordGenerator;
import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Repositories.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {
    private final MailService mailService;
    private final PersonRepository personRepository;

    public PersonService(MailService mailService, PersonRepository personRepository) {
        this.mailService = mailService;
        this.personRepository = personRepository;
    }

    public List<Person> people() {
        return personRepository.findAll();
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

    public List<Person> findByRole(User_Role role) {
        return personRepository.findPersonByPersonRole(role);
    }

    public void resetPassword(String username) {
        Person person = personRepository.findPersonByUsername(username);
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String newPassword = passwordGenerator.generateRandomPassword(8);
        person.setPerson_password(newPassword);

        MailStructure mail = new MailStructure("Запит на зміну пароля", "Ваш новий пароль: " + newPassword);
        mailService.sendMail(person.getEmail(), mail);
        personRepository.save(person);
    }

    public List<Person> byPhone(String phone) {
        return personRepository.findByPhone(phone);
    }
}
