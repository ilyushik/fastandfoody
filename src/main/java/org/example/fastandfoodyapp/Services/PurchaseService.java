package org.example.fastandfoodyapp.Services;

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
}
