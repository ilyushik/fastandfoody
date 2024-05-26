package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.Enumerables.OrderItemStatus;
import org.example.fastandfoodyapp.Model.Order_Item;
import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_ItemRepository extends JpaRepository<Order_Item, Integer> {
    public List<Order_Item> findOrder_ItemsByOrderItemStatusAndPersonId(OrderItemStatus status, Person person);
    public List<Order_Item> findByPurchaseId(Purchase purchase);
}