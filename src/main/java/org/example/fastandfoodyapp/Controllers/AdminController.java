package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Mails.MailService;
import org.example.fastandfoodyapp.Mails.MailStructure;
import org.example.fastandfoodyapp.Model.Enumerables.Status;
import org.example.fastandfoodyapp.Model.Person;
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
    private final MailService mailService;

    public AdminController(PurchaseService purchaseService, MailService mailService) {
        this.purchaseService = purchaseService;
        this.mailService = mailService;
    }

    // main admin page
    @GetMapping("")
    public String adminMain() {
        return "admin/main";
    }

    // restaurant of admin
    @GetMapping("my_restaurant")
    public String restaurant(Model model, @AuthenticationPrincipal PersonDetails personDetails) {
        model.addAttribute("restaurant", personDetails.getPerson().getRestaurant_id());
        return "admin/restaurant";
    }

    // request to delete restaurant
    @PostMapping("/my_restaurant/delete")
    public String deleteRestaurant(@AuthenticationPrincipal PersonDetails personDetails) {
        MailStructure mail = new MailStructure();
        mail.setSubject("Запит на видалення ресторану");
        mail.setMessage("Admin ID: " + personDetails.getPerson().getId() + "\nAdmin name: " + personDetails.getPerson().getName() +
                "\nAdmin surname: " + personDetails.getPerson().getSurname() + "\nAdmin username: " + personDetails.getUsername() +
                "\nAdmin email: " + personDetails.getPerson().getEmail() +
                "\nRestaurant ID: " + personDetails.getPerson().getRestaurant_id().getId());
        mailService.senFromCustomerMail(personDetails.getPerson().getEmail(), mail);
        return "redirect:/admin";
    }

    // orders in restaurant
    @GetMapping("/orders")
    public String orders(Model model, @AuthenticationPrincipal PersonDetails personDetails) {
        int person_id = personDetails.getPerson().getId();
        List<Purchase> purchases = purchaseService.purchases();
        purchases.removeIf(p -> p.getRestaurant_id().getAdmin_id().getId() != person_id);
        // purchases.removeIf(p->p.getStatus().equals(Status.Canceled));

        model.addAttribute("purchases", purchases);
        return "admin/orders";
    }

    // deteils of order
    @GetMapping("/orders/{id}")
    public String orderById(@PathVariable("id") int id, Model model) {
        model.addAttribute("purchase", purchaseService.findById(id));
        return "admin/orderInfo";
    }

    // edit form of status of order
    @GetMapping("/orders/{id}/edit")
    public String editStatus(Model model, @PathVariable("id") int id) {
        model.addAttribute("purchase", purchaseService.findById(id));
        model.addAttribute("statuses", Status.values());
        return "admin/orderEdit";
    }

    // confirmation of editing
    @PostMapping("/submitOrder/{id}")
    public String submitOrder(@ModelAttribute("purchase") Purchase purchase, @PathVariable("id") int id,
                              @AuthenticationPrincipal PersonDetails personDetails) {
        purchaseService.changeStatus(id, purchase.getStatus());
        MailStructure mail = new MailStructure();
        mail.setSubject("Зміна статусу замовлення");
        if (purchase.getStatus().equals(Status.In_progress)) {
            mail.setSubject("Зміна статусу замовлення");
            mail.setMessage("Ваше замовлення готується");
            mailService.sendMail(personDetails.getPerson().getEmail(), mail);
        } else if (purchase.getStatus().equals(Status.Delivered)){
            mail.setSubject("Замовлення отриманно");
            mail.setMessage("");
            mailService.sendMail(personDetails.getPerson().getEmail(), mail);
        } else if (purchase.getStatus().equals(Status.Canceled)) {
            mail.setSubject("Замовлення скасовано");
            mail.setMessage("");
            mailService.sendMail(personDetails.getPerson().getEmail(), mail);
        } else {
            mail.setSubject("Замовлення отриманно");
            mail.setMessage("Ваше замовлення в дорозі");
            mailService.sendMail(personDetails.getPerson().getEmail(), mail);
        }
        return "redirect:/admin/orders/{id}";
    }
}
