package org.example.fastandfoodyapp.Controllers;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.Model.DTO.RestaurantDTO;
import org.example.fastandfoodyapp.Repositories.CityRepository;
import org.example.fastandfoodyapp.Services.RestaurantService;
import org.example.fastandfoodyapp.Services.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainController {
    private ItemService itemService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CityRepository cityRepository;

    @GetMapping()
    public String mainPage() {
        return "client/main";
    }

    @GetMapping("/menu")
    public String items(Model model) {
        model.addAttribute("items", itemService.getAllItemDTO());
        return "client/menu";
    }

    @GetMapping("/map")
    public String maps(Model model) {
        return "client/map";
    }

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

    @GetMapping("/contacts/{id}")
    public String restaurantContact(@PathVariable("id") int id, Model model) {
        model.addAttribute("restContacts", restaurantService.findDTOById(id));
        return "client/restContacts";
    }

    @GetMapping("/about_us")
    public String aboutUs() {
        return "client/aboutUs";
    }
}
