package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Model.Enumerables.Status;
import org.example.fastandfoodyapp.Model.Purchase;
import org.example.fastandfoodyapp.Repositories.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> purchases() {
        return purchaseRepository.findAll();
    }

    public Purchase findById(int id) {
        return purchaseRepository.findPurchaseById(id);
    }

    public void changeStatus(int id, Status status) {
        Purchase purchase = findById(id);
        purchase.setStatus(status);
        purchaseRepository.save(purchase);
    }

    public void cancelOrder(int id) {
        Purchase purchase = findById(id);
        purchase.setStatus(Status.Canceled);
        purchaseRepository.save(purchase);
    }

    public List<Purchase> listById(int id) {
        return purchaseRepository.findById(id);
    }
}
