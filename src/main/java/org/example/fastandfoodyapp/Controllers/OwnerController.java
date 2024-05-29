package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Mails.MailService;
import org.example.fastandfoodyapp.Mails.MailStructure;
import org.example.fastandfoodyapp.Model.*;
import org.example.fastandfoodyapp.Model.DTO.ItemDTO;
import org.example.fastandfoodyapp.Model.DTO.RestaurantDTO;
import org.example.fastandfoodyapp.Model.Enumerables.Category;
import org.example.fastandfoodyapp.Model.Enumerables.User_Role;
import org.example.fastandfoodyapp.Repositories.*;
import org.example.fastandfoodyapp.Security.PersonDetails;
import org.example.fastandfoodyapp.Services.PersonService;
import org.example.fastandfoodyapp.Services.RegistrationService;
import org.example.fastandfoodyapp.Services.RestaurantService;
import org.example.fastandfoodyapp.Services.Service.ItemService;
import org.example.fastandfoodyapp.Services.StorageService;
import org.example.fastandfoodyapp.util.ItemValidation;
import org.example.fastandfoodyapp.util.PersonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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

    @Autowired
    private PersonValidation personValidation;
    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ItemValidation itemValidation;

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
        if (personOrder.isEmpty()) {
            model.addAttribute("error_message", "Немає людини з таким номером телефону...");
            return "ErrorTemplate";
        }
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

    @GetMapping("/city/add")
    public String addCityPage(@ModelAttribute("city") City city) {
        return "owner/addCity";
    }

    @PostMapping("/add/city")
    public String addCity(@ModelAttribute("city") City city, Model model) {
        if (city.getName().isEmpty() || Double.isNaN(city.getLongitude()) || Double.isNaN(city.getLatitude())) {
            model.addAttribute("error_message", "Введіть вірні дані...");
            return "ErrorTemplate";
        }
        City newCity = new City(city.getName(), city.getLongitude(), city.getLatitude());
        cityRepository.save(newCity);
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
    public String confirmAddingAdmin(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidation.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "redirect:/owner/restaurant/" + id;
        }
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

    @GetMapping("/restaurants/addAdmin")
    public String createRestaurantAdmin(@ModelAttribute("person") Person person) {
        return "owner/addAdminRest";
    }

    @PostMapping("/admin/Add")
    public String confirmAdmin(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidation.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "redirect:/owner/restaurants/addAdmin";
        }
        registrationService.registration(person);
        Person admin = personRepository.findPersonByUsername(person.getUsername());
        admin.setPersonRole(User_Role.ROLE_ADMIN);
        personRepository.save(admin);
        return "redirect:/owner/addRestaurant/" + admin.getUsername();
    }

    @GetMapping("/addRestaurant/{username}")
    public String addRestaurant(@ModelAttribute("restaurant") Restaurant restaurant, @PathVariable("username") String username, Model model) {
        City defaultCity = cityRepository.findCityByName("Київ");
        model.addAttribute("defaultCity", defaultCity);
        model.addAttribute("username", username);
        model.addAttribute("cities", cityRepository.findAll());
        return "owner/addRestaurant";
    }

    @PostMapping("restaurants/addRest/{username}")
    public String createRestaurant(@ModelAttribute("restaurant") Restaurant restaurant, @PathVariable("username") String username) {
        Person admin = personRepository.findPersonByUsername(username);
        Restaurant restaurantNew = new Restaurant();
        restaurantNew.setEmail(restaurant.getEmail());
        restaurantNew.setAdminId(admin);
        restaurantNew.setAddress(restaurant.getAddress());
        restaurantNew.setLongitude(restaurant.getLongitude());
        restaurantNew.setLatitude(restaurant.getLatitude());
        restaurantNew.setPhone(restaurant.getPhone());
        restaurantNew.setCityId(restaurant.getCityId());
        restaurantRepository.save(restaurantNew);
        admin.setRestaurant_id(restaurantRepository.findByAdminId(admin));
        personRepository.save(admin);
        return "redirect:/owner/restaurants";
    }

    @GetMapping("/items")
    public String items(@ModelAttribute("item") Item item, Model model) {
        List<Category> categories = new ArrayList<>();
        List<ItemDTO> itemDTOS = itemService.getAllItemDTO();
        for (ItemDTO i : itemDTOS) {
            String image = Base64.getEncoder().encodeToString(storageService.
                    downloadImage(itemService.findItemById(i.getId()).getImage().getName()));
            i.setImage(image);
        }
        List<ItemDTO> coldDrinks = new ArrayList<>();
        List<ItemDTO> hotDrinks = new ArrayList<>();
        List<ItemDTO> beef = new ArrayList<>();
        List<ItemDTO> pork = new ArrayList<>();
        List<ItemDTO> fishAndChicken = new ArrayList<>();
        List<ItemDTO> desserts = new ArrayList<>();
        List<ItemDTO> breakfasts = new ArrayList<>();
        List<ItemDTO> friesAndSauces = new ArrayList<>();
        for (ItemDTO i : itemDTOS) {
            if (i.getCategory().equals(Category.Cold_drinks.getDisplayName())) {
                coldDrinks.add(i);
            } else if (i.getCategory().equals(Category.Hot_drinks.getDisplayName())) {
                hotDrinks.add(i);
            } else if (i.getCategory().equals(Category.Beef.getDisplayName())) {
                beef.add(i);
            } else if (i.getCategory().equals(Category.Pork.getDisplayName())) {
                pork.add(i);
            } else if (i.getCategory().equals(Category.Fish_and_chicken.getDisplayName())) {
                fishAndChicken.add(i);
            } else if (i.getCategory().equals(Category.Desserts.getDisplayName())) {
                desserts.add(i);
            } else if(i.getCategory().equals(Category.Breakfasts.getDisplayName())) {
                breakfasts.add(i);
            } else  {
                friesAndSauces.add(i);
            }
        }
        model.addAttribute("coldDrinks", coldDrinks);
        model.addAttribute("hotDrinks", hotDrinks);
        model.addAttribute("beef", beef);
        model.addAttribute("pork", pork);
        model.addAttribute("fishAndChicken", fishAndChicken);
        model.addAttribute("desserts", desserts);
        model.addAttribute("breakfast", breakfasts);
        model.addAttribute("friesAndSauces", friesAndSauces);

        categories.add(Category.Beef);
        categories.add(Category.Pork);
        categories.add(Category.Desserts);
        categories.add(Category.Cold_drinks);
        categories.add(Category.Breakfasts);
        categories.add(Category.Fish_and_chicken);
        categories.add(Category.Fries_and_sauces);
        categories.add(Category.Hot_drinks);

        model.addAttribute("categories", categories);
        model.addAttribute("defaultCategory", Category.Beef);
        return "owner/items";
    }

    @GetMapping("/items/{id}")
    public String itemInfo(@PathVariable("id") int id, Model model) {
        Item item = itemService.findItemById(id);
        String image = Base64.getEncoder().encodeToString(storageService.downloadImage(item.getImage().getName()));
        model.addAttribute("item", item);
        model.addAttribute("image", image);
        return "owner/itemDetails";
    }

    @PostMapping("item/add")
    public String addItem(@ModelAttribute("item") @Valid Item item, BindingResult bindingResult,
                          @RequestParam("file") MultipartFile file, Model model) throws IOException {
        itemValidation.validate(item, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/owner/items";
        }
        if (item.getItem_name().isEmpty() || item.getPrice() < 1 || item.getDescription().isEmpty() || file == null
        || item.getPrep_time() < 1) {
            model.addAttribute("error_message", "Введіть вірні дані...");
            return "ErrorTemplate";
        }
        String imageName = storageService.uploadImage(file);
        Image image = storageRepository.findByName(imageName).orElseThrow();
        item.setImage(image);

        itemRepository.save(item);

        return "redirect:/owner/items";
    }

    @PostMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable("id") int id) {
        itemService.deleteById(id);
        return "redirect:/owner/items";
    }

    @PostMapping("/item/edit/{id}")
    public String editItem(@PathVariable("id") int id, @ModelAttribute("item") Item item) {
        Item newItem = itemService.findItemById(id);

        newItem.setItem_name(item.getItem_name());
        newItem.setPrice(item.getPrice());
        newItem.setDescription(item.getDescription());
        itemRepository.save(newItem);
        return "redirect:/owner/items";
    }

    @PostMapping("/item/editImage/{id}")
    public String editImage(@RequestParam("image") MultipartFile image, @PathVariable("id") int id) throws IOException {
        String imageName = storageService.uploadImage(image);

        Item item = itemService.findItemById(id);
        Image oldImage = item.getImage();
        Image image1 = storageRepository.findByName(imageName).orElseThrow();

        item.setImage(image1);

        itemRepository.save(item);
        storageRepository.delete(oldImage);
        return "redirect:/owner/items";
    }
}
