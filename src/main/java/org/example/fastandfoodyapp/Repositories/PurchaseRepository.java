package org.example.fastandfoodyapp.Repositories;

import org.example.fastandfoodyapp.Model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    public Purchase findPurchaseById(int id);

    public List<Purchase> findById(int id);
}