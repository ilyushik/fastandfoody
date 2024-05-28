package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.Promo_Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Promo_CodeRepository extends JpaRepository<Promo_Code, Integer> {
    public Promo_Code findByCode(String code);
}