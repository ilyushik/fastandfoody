package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Model.Enumerables.Status;
import org.example.fastandfoodyapp.Model.Purchase;
import org.example.fastandfoodyapp.Security.PersonDetails;
import org.example.fastandfoodyapp.Services.PurchaseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PurchaseService purchaseService;

    public AdminController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("")
    public String adminMain() {
        return "admin/main";
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
        // purchases.removeIf(p->p.getStatus().equals(Status.Canceled));

        model.addAttribute("purchases", purchases);
        return "admin/orders";
    }

    @GetMapping("/orders/{id}")
    public String orderById(@PathVariable("id") int id, Model model) {
        model.addAttribute("purchase", purchaseService.findById(id));
        return "admin/orderInfo";
    }

    @GetMapping("/orders/{id}/edit")
    public String editStatus(Model model, @PathVariable("id") int id) {
        model.addAttribute("purchase", purchaseService.findById(id));
        model.addAttribute("statuses", Status.values());
        return "admin/orderEdit";
    }

    @PostMapping("/submitOrder/{id}")
    public String submitOrder(@ModelAttribute("purchase") Purchase purchase, @PathVariable("id") int id) {
        purchaseService.changeStatus(id, purchase.getStatus());
        return "redirect:/admin/orders/{id}";
    }
}
