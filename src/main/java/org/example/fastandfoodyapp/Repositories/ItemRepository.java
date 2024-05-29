package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query("SELECT i FROM Item i where i.item_name = :name")
    public Item findByItem_name(@Param("name") String name);
}