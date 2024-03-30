package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Model.Purchase;
import org.example.fastandfoodyapp.Security.PersonDetails;
import org.example.fastandfoodyapp.Services.PurchaseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PurchaseService purchaseService;

    public AdminController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("my_restaurant")
    public String restaurant(Model model, @AuthenticationPrincipal PersonDetails personDetails) {
        model.addAttribute("restaurant", personDetails.getPerson().getRestaurant_id());
        return "admin/restaurant";
    }

    @GetMapping("/orders")
    public String orders(Model model, @AuthenticationPrincipal PersonDetails personDetails) {
        int person_id = personDetails.getPerson().getId();
        List<Purchase> purchases = purchaseService.purchases();
        purchases.removeIf(p -> p.getRestaurant_id().getAdmin_id().getId() != person_id);

        model.addAttribute("purchases", purchases);
        return "admin/orders";
    }
}
