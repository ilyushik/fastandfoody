package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Mails.MailService;
import org.example.fastandfoodyapp.Mails.MailStructure;
import org.example.fastandfoodyapp.Model.Enumerables.Status;
import org.example.fastandfoodyapp.Model.Order_Item;
import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Model.Purchase;
import org.example.fastandfoodyapp.Model.Restaurant;
import org.example.fastandfoodyapp.Repositories.Order_ItemRepository;
import org.example.fastandfoodyapp.Security.PersonDetails;
import org.example.fastandfoodyapp.Services.PersonService;
import org.example.fastandfoodyapp.Services.PurchaseService;
import org.example.fastandfoodyapp.Services.RestaurantService;
import org.example.fastandfoodyapp.Services.StorageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PurchaseService purchaseService;
    private final MailService mailService;
    private final RestaurantService restaurantService;
    private final PersonService personService;
    private final StorageService storageService;
    private final Order_ItemRepository orderItemRepository;

    public AdminController(PurchaseService purchaseService, MailService mailService, RestaurantService restaurantService, PersonService personService, StorageService storageService, Order_ItemRepository orderItemRepository) {
        this.purchaseService = purchaseService;
        this.mailService = mailService;
        this.restaurantService = restaurantService;
        this.personService = personService;
        this.storageService = storageService;
        this.orderItemRepository = orderItemRepository;
    }

    // main admin page
    @GetMapping("")
    public String adminMain(Model model, @AuthenticationPrincipal PersonDetails personDetails) {
        Person person = personService.findById(personDetails.getPerson().getId());
        List<Person> people = new ArrayList<>();
        model.addAttribute("restaurant", restaurantService.byId(personDetails.getPerson().getRestaurant_id().getId()));
        model.addAttribute("admin", person);
        model.addAttribute("people", people);
        return "admin/main";
    }

    // find purchase by number
    @PostMapping("/filter")
    public String orderFilter(@RequestParam("phone") String phone, Model model, @AuthenticationPrincipal PersonDetails personDetails) {
        List<Person> personOrder = personService.byPhone(phone);
        if (personOrder.isEmpty()) {
            model.addAttribute("error_message", "Немає людини з таким номером телефону...");
            return "ErrorTemplate";
        }
        Person person = personService.findById(personDetails.getPerson().getId());
        model.addAttribute("restaurant", restaurantService.byId(personDetails.getPerson().getRestaurant_id().getId()));
        model.addAttribute("admin", person);
        model.addAttribute("people", personOrder);
        return "admin/main";
    }

    @GetMapping("/person/{personId}/orders")
    public String personOrders(@PathVariable("personId") int personId, Model model) {
        List<Purchase> purchases = purchaseService.purchases();
        purchases.removeIf(p -> p.getPerson_id().getId() != personId);
        model.addAttribute("purchases", purchases);
        return "admin/personOrders";
    }

    // detail page purchase
    @GetMapping("/person/{personId}/orders/{orderId}")
    public String orderDetails(@PathVariable("orderId") int orderId, Model model, @PathVariable("personId") int personId) {
        Purchase purchase = purchaseService.findById(orderId);
        List<Order_Item> orderItems = orderItemRepository.findByPurchaseId(purchase);
        for (Order_Item o : orderItems) {
            o.setStringImage(Base64.getEncoder().encodeToString(storageService.downloadImage(o.getItem_id().getImage().getName())));
            o.setSum(o.getCount(), o.getItem_id().getPrice());
        }
        purchase.setPrice(orderItems);
        model.addAttribute("purchase", purchase);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("items_order", orderItems);
        return "admin/orderDetails";
    }

    // confirmation of editing
    @PostMapping("/submitOrder/{id}")
    public String submitOrder(@ModelAttribute("purchase") Purchase purchase, @PathVariable("id") int id,
                              @AuthenticationPrincipal PersonDetails personDetails) {
        Person user = purchaseService.findById(id).getPerson_id();
        purchaseService.changeStatus(id, purchase.getStatus());
        MailStructure mail = new MailStructure();
        mail.setSubject("Зміна статусу замовлення");
        if (purchase.getStatus().equals(Status.In_progress)) {
            mail.setSubject("Зміна статусу замовлення");
            mail.setMessage("Ваше замовлення готується");
            mailService.sendMail(user.getEmail(), mail);
        } else if (purchase.getStatus().equals(Status.Delivered)){
            mail.setSubject("Замовлення отриманно");
            mail.setMessage("");
            mailService.sendMail(user.getEmail(), mail);
        } else if (purchase.getStatus().equals(Status.Canceled)) {
            mail.setSubject("Замовлення скасовано");
            mail.setMessage("");
            mailService.sendMail(user.getEmail(), mail);
        } else {
            mail.setSubject("Зміна статусу замовлення");
            mail.setMessage("Ваше замовлення в дорозі");
            mailService.sendMail(user.getEmail(), mail);
        }
        return "redirect:/admin";
    }

    // edit restaurant info
    @PostMapping("/restaurantEdit")
    public String editRestaurant(@ModelAttribute("restaurant") Restaurant restaurant, @AuthenticationPrincipal PersonDetails personDetails) {
        int id = personDetails.getPerson().getRestaurant_id().getId();
        restaurantService.editRestaurant(id, restaurant);
        return "redirect:/admin";
    }

    // edit admin info
    @PostMapping("/adminEdit")
    public String editAdmin(@ModelAttribute("person") Person person, @AuthenticationPrincipal PersonDetails personDetails) {
        int id = personDetails.getPerson().getId();
        personService.editInfo(person, id);
        MailStructure mail = new MailStructure("Зміна даних", "Ваші дані змінено успішно");
        mailService.sendMail(person.getEmail(), mail);
        return "redirect:/admin";
    }

}
