package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}