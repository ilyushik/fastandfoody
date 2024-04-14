package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Model.Enumerables.User_Role;
import org.example.fastandfoodyapp.Model.Image;
import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Repositories.PersonRepository;
import org.example.fastandfoodyapp.Repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class RegistrationService {
    private final PersonRepository personRepository;
    private final StorageService storageService;
    private final StorageRepository storageRepository;
    @Autowired
    public RegistrationService(PersonRepository personRepository, StorageService storageService, StorageRepository storageRepository) {
        this.personRepository = personRepository;
        this.storageService = storageService;
        this.storageRepository = storageRepository;
    }

    // registration with uploading image
//    @Transactional
//    public void registration(Person person, MultipartFile file) throws IOException {
//        storageService.uploadImage(file);
//        person.setPersonRole(User_Role.ROLE_CLIENT);
//        Image image = storageRepository.findByName(file.getOriginalFilename()).orElseThrow();
//        person.setImage(image);
//        personRepository.save(person);
//    }

    @Transactional
    public void registration(Person person) {
        person.setPersonRole(User_Role.ROLE_CLIENT);
        person.setImage(storageRepository.findByName("default.png").orElseThrow());
        personRepository.save(person);
    }
}
