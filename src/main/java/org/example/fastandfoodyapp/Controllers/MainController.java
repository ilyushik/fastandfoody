package org.example.fastandfoodyapp.Controllers;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.Mails.MailService;
import org.example.fastandfoodyapp.Mails.MailStructure;
import org.example.fastandfoodyapp.Model.DTO.RestaurantDTO;
import org.example.fastandfoodyapp.Model.Enumerables.Status;
import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Model.Purchase;
import org.example.fastandfoodyapp.Repositories.CityRepository;
import org.example.fastandfoodyapp.Security.PersonDetails;
import org.example.fastandfoodyapp.Services.PersonService;
import org.example.fastandfoodyapp.Services.PurchaseService;
import org.example.fastandfoodyapp.Services.RestaurantService;
import org.example.fastandfoodyapp.Services.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {
    private ItemService itemService;
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private MailService mailService;

    @Autowired
    private PurchaseService purchaseService;

    // main page
//    @GetMapping()
//    public String mainPage() {
//        return "client/main";
//    }

    @GetMapping("/")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (request.isUserInRole("ROLE_OWNER")) {
            return "redirect:/owner";
        }
        return "client/main";
    }

    // menu with items
    @GetMapping("/menu")
    public String items(Model model) {
        model.addAttribute("items", itemService.getAllItemDTO());
        return "client/menu";
    }

    @GetMapping("/menu/{id}")
    public String itemDetails(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.findItemById(id));
        return "client/itemDetails";
    }

    // map with restaurants
    @GetMapping("/map")
    public String maps(Model model) {
        return "client/map";
    }

    // find contacts of restaurants
    @GetMapping("/contacts")
    public String contacts(@RequestParam(name = "city", required = false) String city, Model model) {
        List<RestaurantDTO> restaurants;
        if (city != null && !city.isEmpty()) {
            restaurants = restaurantService.findRestaurantByCity(city);
        } else {
            restaurants = restaurantService.restaurantsDTO();
        }
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("cities", cityRepository.findAll());
        return "client/contacts";
    }

    // contacts of current restaurant
    @GetMapping("/contacts/{id}")
    public String restaurantContact(@PathVariable("id") int id, Model model) {
        model.addAttribute("restContacts", restaurantService.findDTOById(id));
        return "client/restContacts";
    }

    // about restaurant
    @GetMapping("/about_us")
    public String aboutUs() {
        return "client/aboutUs";
    }

    // person's info
    @GetMapping("/my_info")
    public String account(@AuthenticationPrincipal PersonDetails personDetails, Model model) {
        Person person = personService.findById(personDetails.getPerson().getId());
        List<Purchase> usersActivePurchases = new ArrayList<>(person.getPurchases());

        Iterator<Purchase> iterator = usersActivePurchases.iterator();
        while (iterator.hasNext()) {
            Purchase p = iterator.next();
            if (!p.getPerson_id().equals(person)) {
                iterator.remove();
            } else {
                if (p.getStatus().equals(Status.Delivered) || p.getStatus().equals(Status.Canceled)) {
                    iterator.remove();
                }
            }
        }

        model.addAttribute("person", person);
        model.addAttribute("purchases", usersActivePurchases);
        return "client/account";
    }


    // edit person info
    @GetMapping("/my_info/edit")
    public String edit(@AuthenticationPrincipal PersonDetails personDetails, Model model) {
        model.addAttribute("person", personDetails.getPerson());
        return "client/editPerson";
    }

    // confirm editing
    @PostMapping("/my_info/{id}/uploadInfo")
    public String upload(@ModelAttribute("person") Person person, @PathVariable("id") int id, @AuthenticationPrincipal PersonDetails personDetails) {
        personService.editInfo(person, id);
        MailStructure mail = new MailStructure("Ви успішно змінили ваші дані", "");
        mailService.sendMail(personDetails.getPerson().getEmail(), mail);
        return "redirect:/my_info";
    }

    // delete account
    @PostMapping("/my_info/delete")
    public String deleteAccount(HttpServletRequest request, @AuthenticationPrincipal PersonDetails personDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, null, auth);
        }

        MailStructure mail = new MailStructure("Ви видалили свій акаунт", "");
        mailService.sendMail(personDetails.getPerson().getEmail(), mail);

        personService.deletePerson(personDetails.getPerson().getId());
        return "redirect:/";
    }

    // person's orders
    @GetMapping("/my_info/orders")
    public String personOrders(Model model, @AuthenticationPrincipal PersonDetails personDetails) {
        Person person = personService.findById(personDetails.getPerson().getId());
        List<Purchase> userPurchases = person.getPurchases();
        model.addAttribute("purchases", userPurchases);
        return "client/orders";
    }

    // client can see order details by id
    @GetMapping("/my_info/orders/{id}")
    public String detailInfo(@PathVariable("id") int id, Model model) {
        model.addAttribute("purchase", purchaseService.findById(id));
        return "client/detailOrder";
    }

    // cancel order
    @PostMapping("/order/{id}/cancel")
    public String cancelOrder(@PathVariable("id") int id) {
        purchaseService.cancelOrder(id);
        return "redirect:/my_info/orders";
    }

    // form to reset password
    @GetMapping("/forget_password")
    public String forgetPassword() {
        return "client/forgetPassword";
    }

    // reset password
    @PostMapping("/reset_password")
    public String resetPassword(@RequestParam("username") String username) {
        personService.resetPassword(username);
        return "redirect:/auth/login";
    }
}
