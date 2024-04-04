package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.Enumerables.User_Role;
import org.example.fastandfoodyapp.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    public Optional<Person> findByUsername(String username);

    public Optional<Person> findPersonByEmail(String email);

    public Optional<Person> findPersonByPhone(String phone);

    public List<Person> findPersonByPersonRole(User_Role role);

    public Person findPersonByUsername(String username);
}
