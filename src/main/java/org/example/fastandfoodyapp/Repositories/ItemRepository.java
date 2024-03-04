package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}