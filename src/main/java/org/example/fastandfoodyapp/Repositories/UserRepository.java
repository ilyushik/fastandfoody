package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.System_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<System_User, Integer> {
}
