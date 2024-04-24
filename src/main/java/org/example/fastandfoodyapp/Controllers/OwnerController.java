package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Model.Enumerables.User_Role;
import org.example.fastandfoodyapp.Model.Item;
import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Model.Restaurant;
import org.example.fastandfoodyapp.Repositories.ItemRepository;
import org.example.fastandfoodyapp.Repositories.PersonRepository;
import org.example.fastandfoodyapp.Repositories.RestaurantRepository;
import org.example.fastandfoodyapp.Services.PersonService;
import org.example.fastandfoodyapp.Services.RestaurantService;
import org.example.fastandfoodyapp.Services.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private PersonService personService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public String mainOwner() {
        return "owner/main";
    }

    //Workflow with admins
    @GetMapping("/admins")
    public String pageWithAdmins(Model model) {
        List<Person> admins = personService.findByRole(User_Role.ROLE_ADMIN);
        model.addAttribute("admins", admins);
        return "owner/admins";
    }

    @PostMapping("/admins/filter")
    public String filterAdminByPhone(@RequestParam("phone") String phone, Model model) {
        List<Person> adminsList = personService.byPhone(phone).
                stream().
                filter(p -> p.getPersonRole() == User_Role.ROLE_ADMIN).
                toList();
        model.addAttribute("admins", adminsList);
        return "owner/admins";
    }

    @PostMapping("/admins/delete/{id}")
    public String deleteAdmin(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return "redirect:/owner/admins";
    }

    @GetMapping("/admins/{adminId}")
    public String getFullAdminInfo(@PathVariable("adminId") int adminId, Model model) {
        Person p = personService.findById(adminId);
        model.addAttribute("admin", p);
        return "owner/adminDetails";
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
    public String getAllRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("restaurants", restaurants);
        return "owner/restaurants";
    }

    @GetMapping("restaurants/add")
    public String createRestaurant() {
        return "owner/addRestaurant";
    }

    @PostMapping("restaurants/add")
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
