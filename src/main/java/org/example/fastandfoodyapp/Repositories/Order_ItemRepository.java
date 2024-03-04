package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.Order_Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_ItemRepository extends JpaRepository<Order_Item, Integer> {
}