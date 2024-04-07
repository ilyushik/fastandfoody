package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.Restaurant;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("select r from Restaurant r where r.city_id = :id")
    public List<Restaurant> findByCity_id(@Param("id") int id);
}