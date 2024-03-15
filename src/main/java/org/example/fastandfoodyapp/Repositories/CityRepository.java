package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    public City findCityByName(String city);
}
