package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Mails.MailService;
import org.example.fastandfoodyapp.Mails.MailStructure;
import org.example.fastandfoodyapp.Model.City;
import org.example.fastandfoodyapp.Model.DTO.RestaurantDTO;
import org.example.fastandfoodyapp.Model.Enumerables.User_Role;
import org.example.fastandfoodyapp.Model.Item;
import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Model.Restaurant;
import org.example.fastandfoodyapp.Repositories.CityRepository;
import org.example.fastandfoodyapp.Repositories.ItemRepository;
import org.example.fastandfoodyapp.Repositories.PersonRepository;
import org.example.fastandfoodyapp.Repositories.RestaurantRepository;
import org.example.fastandfoodyapp.Security.PersonDetails;
import org.example.fastandfoodyapp.Services.PersonService;
import org.example.fastandfoodyapp.Services.RegistrationService;
import org.example.fastandfoodyapp.Services.RestaurantService;
import org.example.fastandfoodyapp.Services.Service.ItemService;
import org.example.fastandfoodyapp.Services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private PersonService personService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("")
    public String mainOwner(Model model, @AuthenticationPrincipal PersonDetails personDetails) {
        Person owner = personService.findById(personDetails.getPerson().getId());
        model.addAttribute("owner", owner);
        return "owner/main";
    }

    @PostMapping("/editOwner")
    public String editOwner(@ModelAttribute("owner") Person owner, @AuthenticationPrincipal PersonDetails personDetails) {
        int id = personDetails.getPerson().getId();
        personService.editInfo(owner, id);
        MailStructure mail = new MailStructure("Зміна даних", "Ваші дані змінено успішно");
        mailService.sendMail(owner.getEmail(), mail);
        return "redirect:/owner";
    }

    //Workflow with admins
    @GetMapping("/admins")
    public String pageWithAdmins(Model model) {
        List<Person> admins = personService.findByRole(User_Role.ROLE_ADMIN);

        for (Person p : admins) {
            p.setView_image(Base64.getEncoder().encodeToString(storageService.downloadImage(p.getImage().getName())));
        }
        model.addAttribute("admins", admins);
        return "owner/admins";
    }

    @PostMapping("/admins/filter")
    public String filterAdminByPhone(@RequestParam("phone") String phone, Model model) {
        List<Person> personOrder = personService.byPhone(phone);
        for (Person p: personOrder) {
            p.setView_image(Base64.getEncoder().encodeToString(storageService.downloadImage(p.getImage().getName())));
        }
        model.addAttribute("admins", personOrder);
        return "owner/admins";
    }

    @PostMapping("/admins/delete/{id}")
    public String deleteAdmin(@PathVariable("id") int id) {
        String email = personService.findById(id).getEmail();
        personService.deletePerson(id);
        MailStructure mail = new MailStructure("Ваш аккаунт видалено", "");
        mailService.sendMail(email, mail);
        return "redirect:/owner/admins";
    }

    @GetMapping("/admins/{adminId}")
    public String getFullAdminInfo(@PathVariable("adminId") int adminId, Model model) {
        Person p = personService.findById(adminId);
        p.setView_image(Base64.getEncoder().encodeToString(storageService.downloadImage(p.getImage().getName())));
        model.addAttribute("admin", p);
        return "owner/adminDetails";
    }


    // delete this method
//    @GetMapping("/admins/addAdmin")
//    public String addAdminPage(Model model) {
//        List<Person> adminsList = personService.findByRole(User_Role.ROLE_CLIENT);
//        for (Person p: adminsList) {
//            p.setView_image(Base64.getEncoder().encodeToString(storageService.downloadImage(p.getImage().getName())));
//        }
//        model.addAttribute("admins", adminsList);
//        return "owner/addAdmin";
//    }

    @PostMapping("/admins/addAdmin/filter")
    public String findNewAdminByPhone(@RequestParam("phone") String phone, Model model) {
        Optional<Person> p = personService.byPhone(phone).stream().findFirst();
        Person person = null;
        if (p.isPresent()) {
            person = p.get();
            person.setView_image(Base64.getEncoder().encodeToString(storageService.downloadImage(person.getImage().getName())));
        }
        model.addAttribute("person", person);
        return "owner/addAdmin";
    }

    @PostMapping("admins/addAdmin/{personId}")
    public String addAdmin(@PathVariable("personId") int id) {
        Person p = personService.findById(id);
        p.setPersonRole(User_Role.ROLE_ADMIN);
        personRepository.save(p);
        return "redirect:/owner/admins";
    }

    //Workflow with restaurants
    @PostMapping("/restaurants/delete/{id}")
    public String deleteRestaurant(@PathVariable("id") int id) {
        restaurantService.deleteById(id);
        return "redirect:/owner/restaurants";
    }

    @GetMapping("/restaurants")
    public String getAllRestaurants(@RequestParam(name = "city", defaultValue = "Київ") String city, Model model) {
        City defaultCity = cityRepository.findCityByName("Київ");
        List<RestaurantDTO> restaurants;
        boolean filter = false;
        if (city != null && !city.isEmpty()) {
            restaurants = restaurantService.findRestaurantByCity(city);
            filter = true;
        } else {
            restaurants = restaurantService.restaurantsDTO();
            filter = false;
        }
        model.addAttribute("filteredCity", cityRepository.findCityByName(city));
        model.addAttribute("filter", filter);
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("defaultCity", defaultCity);
        return "owner/restaurants";
    }

    @GetMapping("/restaurants/{id}")
    public String restaurantDetails(Model model, @PathVariable("id") int id, @ModelAttribute("person") Person person) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();

        model.addAttribute("restaurant", restaurant);
        return "owner/restaurantDetails";
    }

    @PostMapping("/confirm/{id}")
    public String confirmAddingAdmin(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        registrationService.registration(person);
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        Person person1 = personRepository.findPersonByUsername(person.getUsername());
        person1.setPersonRole(User_Role.ROLE_ADMIN);
        restaurant.setAdminId(person1);
        personRepository.save(person1);
        restaurantRepository.save(restaurant);
        MailStructure mail = new MailStructure("Повідомлення про зміни", "Ви стали адміном ресторану №" + restaurant.getId());
        mailService.sendMail(person1.getEmail(), mail);
        return "redirect:/owner/restaurants/{id}";
    }

    @GetMapping("restaurants/add")
    public String createRestaurant() {
        return "owner/addRestaurant";
    }

    @PostMapping("restaurants/addRest")
    public String createRestaurant(@ModelAttribute("restaurant") Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return "redirect:/owner/restaurants";
    }

    @GetMapping("item/add")
    public String addItem() {
        return "owner/addItem";
    }

    @PostMapping("item/add")
    public String addItem(@ModelAttribute("item") Item item) {
        Item i = new Item();
        i.setItem_name(item.getItem_name());
        i.setPrice(item.getPrice());
        i.setDescription(item.getDescription());
        i.setPrep_time(item.getPrep_time());
        i.setImage(item.getImage());
        i.setCategory(item.getCategory());
        itemRepository.save(i);
        return "redirect:/owner/items";
    }

    @PostMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable("id") int id) {
        itemService.deleteById(id);
        return "redirect:/owner/items";
    }

    @PostMapping("/item/filter")
    public String findItem(@RequestParam("name") String name, Model model) {
        List<Item> items = itemService.findByName(name);
        model.addAttribute("items", items);
        return "owner/items";
    }

    @PostMapping("item/edit/{id}")
    public String editItem(@PathVariable("id") int id, Item new_item) {
        Item item = itemService.findItemById(id);
        item.setItem_name(new_item.getItem_name());
        item.setPrice(new_item.getPrice());
        item.setDescription(new_item.getDescription());
        item.setPrep_time(new_item.getPrep_time());
        item.setImage(new_item.getImage());
        item.setCategory(new_item.getCategory());
        itemRepository.save(item);
        return "redirect:/owner/items";
    }
}
