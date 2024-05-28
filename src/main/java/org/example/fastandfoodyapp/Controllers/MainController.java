package org.example.fastandfoodyapp.Controllers;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.Mails.MailService;
import org.example.fastandfoodyapp.Mails.MailStructure;
import org.example.fastandfoodyapp.Model.*;
import org.example.fastandfoodyapp.Model.DTO.ItemDTO;
import org.example.fastandfoodyapp.Model.DTO.RestaurantDTO;
import org.example.fastandfoodyapp.Model.Enumerables.*;
import org.example.fastandfoodyapp.Repositories.*;
import org.example.fastandfoodyapp.Security.PersonDetails;
import org.example.fastandfoodyapp.Services.*;
import org.example.fastandfoodyapp.Services.Service.ItemService;
import org.example.fastandfoodyapp.util.PersonValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

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

    @Autowired
    private StorageService storageService;

    @Autowired
    private ItemServiceImpl itemServiceImpl;
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PersonValidation personValidation;

    @Autowired
    private Order_ItemRepository orderItemRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private Promo_CodeRepository promo_CodeRepository;

    // main page
//    @GetMapping()
//    public String mainPage() {
//        return "client/main";
//    }

    @GetMapping("/RazRazRazEtoHardBass")
    public String addOwner(@ModelAttribute("person") Person person) {
        return "client/addOwner";
    }

    @PostMapping("/add_owner")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @RequestParam("file") MultipartFile file) throws IOException {
        storageService.uploadImage(file);
        personValidation.validate(person, bindingResult);
        if(bindingResult.hasErrors()) {
            return "redirect:/RazRazRazEtoHardBass";
        }
        registrationService.registration(person);
        Person owner = personRepository.findPersonByUsername(person.getUsername());
        owner.setPersonRole(User_Role.ROLE_OWNER);
        Image image = storageRepository.findByName(file.getOriginalFilename()).orElseThrow();
        owner.setImage(image);
        personRepository.save(owner);
        MailStructure mail = new MailStructure("Реєстрація успішна", person.getName() +
                ", вітаємо Вас у нашому ресторані");
        mailService.sendMail(person.getEmail(), mail);
        return "redirect:/auth/login";
    }

    @GetMapping("/form")
    public String uploadForm() {
        return "mainForm";
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        storageService.uploadImage(image);
        return "redirect:/form";
    }

    @GetMapping("/")
    public String defaultAfterLogin(HttpServletRequest request, @AuthenticationPrincipal PersonDetails personDetails, Model model) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (request.isUserInRole("ROLE_OWNER")) {
            return "redirect:/owner";
        }
        boolean authenticated = false;
        if (personDetails != null) {
            authenticated = true;
        } else {
            authenticated = false;
        }
        model.addAttribute("authenticated", authenticated);
        return "client/main";
    }

    // menu with items
    @GetMapping("/menu")
    public String items(Model model) {
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
        return "client/menu";
    }

    // find contacts of restaurants
    @GetMapping("/contacts")
    public String contacts(@RequestParam(name = "city", defaultValue = "Київ") String city, Model model) {
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
        List<Purchase> userPurchases = new ArrayList<>(person.getPurchases());
        List<Purchase> usersActivePurchases = new ArrayList<>(person.getPurchases());

        userPurchases.removeIf(p -> p.getStatus().equals(Status.In_progress) || p.getStatus().equals(Status.On_way));
        for (Purchase p : userPurchases) {
            p.setPrice(p.getOrder_item_id());
        }

        usersActivePurchases.removeIf(p -> p.getStatus().equals(Status.Delivered) || p.getStatus().equals(Status.Canceled));
        for (Purchase p : usersActivePurchases) {
            p.setPrice(p.getOrder_item_id());
        }

        String logo = Base64.getEncoder().encodeToString(storageService.downloadImage(person.getImage().getName()));
        model.addAttribute("logo", logo);
        model.addAttribute("person", person);
        model.addAttribute("activePurchases", usersActivePurchases);
        model.addAttribute("purchases", userPurchases);
        return "client/account";
    }


    // edit person info
    @GetMapping("/my_info/edit")
    public String edit(@AuthenticationPrincipal PersonDetails personDetails, Model model) {
        Person person = personDetails.getPerson();
        Person person1 = personService.findById(person.getId());
        String image = Base64.getEncoder().encodeToString(storageService.downloadImage(person.getImage().getName()));
        model.addAttribute("person", person1);
        model.addAttribute("image", image);
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

    @PostMapping("/my_info/{id}/uploadImage")
    public String imageEdit(@PathVariable("id") int id, @RequestParam("file") MultipartFile file, @AuthenticationPrincipal PersonDetails personDetails) throws IOException {
        storageService.uploadImage(file);
        Image image = storageRepository.findByName(file.getOriginalFilename()).orElseThrow();
        Person person = personDetails.getPerson();
        String imageBefore = person.getImage().getName();
        Image imageToDelete = storageRepository.findByName(imageBefore).orElseThrow();
        person.setImage(image);
        personRepository.save(person);
        if (!imageBefore.equals("default.png")) {
            storageRepository.delete(imageToDelete);
        }
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

        Image image = personDetails.getPerson().getImage();
        String imageName = image.getName();
        personService.deletePerson(personDetails.getPerson().getId());
        if (!imageName.equals("default.png")) {
            storageRepository.delete(image);
        }
        return "redirect:/";
    }

    // client can see order details by id
    @GetMapping("/my_info/orders/{id}")
    public String detailInfo(@PathVariable("id") int id, Model model) {
        Purchase purchase = purchaseService.findById(id);
        boolean address = false;
        if (purchase.getAddress() != null) {
            address = true;
        }
        purchase.setPrice(purchase.getOrder_item_id());
        for (Order_Item i : purchase.getOrder_item_id()) {
            i.setStringImage(Base64.getEncoder().encodeToString(storageService.downloadImage(i.getItem_id().getImage().getName())));
            i.setSum(i.getCount(), i.getItem_id().getPrice());
        }
        model.addAttribute("purchase", purchase);
        model.addAttribute("address", address);
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
    public String resetPassword(@RequestParam("username") String username, Model model) {
        if (personRepository.findPersonByUsername(username) == null) {
            model.addAttribute("error_message", "Користувача з такими юзернеймом не знайдено...");
            return "ErrorTemplate";
        }
        personService.resetPassword(username);
        return "redirect:/auth/login";
    }

    //page with map
    @GetMapping("/order")
    public String makeOrder(@RequestParam(name = "city", defaultValue = "Київ") String city, Model model) {
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
        return "client/order";
    }

    //Showing menu to a client
    @GetMapping("/order/{restaurantId}")
    public String showMenu(Model model, @PathVariable("restaurantId") int restaurantId,
                           @AuthenticationPrincipal PersonDetails personDetails, @ModelAttribute("purchase") Purchase purchase) {
        List<ItemDTO> itemDTOS = itemService.getAllItemDTO();
        for (ItemDTO i : itemDTOS) {
            String image = Base64.getEncoder().encodeToString(storageService.
                    downloadImage(itemService.findItemById(i.getId()).getImage().getName()));
            i.setImage(image);
        }

        List<Order_Item> items = orderItemRepository.findAll();
        List<Order_Item> needs_items = new ArrayList<>();
        double active_sum = 0.0;
        int active_count = 0;
        int check_count = 0;
        for (Order_Item o : items) {
            if (o.getPersonId().getId() == personDetails.getPerson().getId()) {
                if (o.getOrderItemStatus() == OrderItemStatus.ACTIVE) {
                    needs_items.add(o);
                    active_sum += o.getPrice();
                    active_count++;
                    o.setString_image(Base64.getEncoder().encodeToString(storageService.downloadImage(o.getItem_id().getImage().getName())));
                    check_count += o.getCount();
                }
            }
        }

        model.addAttribute("active_items", needs_items);
        model.addAttribute("active_sum", active_sum);
        model.addAttribute("active_count", active_count);

        model.addAttribute("check_count", check_count);

        model.addAttribute("payments", Payment_Way.values());
        model.addAttribute("defaultPayment", Payment_Way.Cash);

        model.addAttribute("deliveries", Delivery_Way.values());
        model.addAttribute("defaultDelivery", Delivery_Way.Delivery);






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
        model.addAttribute("restaurant_number", restaurantId);
        return "client/orderMenu";
    }

    @PostMapping("/add-orderItem/{id}/{restaurantId}")
    public String addToList(@RequestParam("count") int count, @PathVariable("id") int id,
                            @AuthenticationPrincipal PersonDetails personDetails, @PathVariable("restaurantId") int restaurantId) {
        Item item = itemService.findItemById(id);
        Order_Item newOrderItem = new Order_Item();
        newOrderItem.setCount(count);
        newOrderItem.setPrep_time(count * item.getPrep_time());
        newOrderItem.setPrice(count * item.getPrice());
        newOrderItem.setItem_id(item);
        newOrderItem.setPersonId(personDetails.getPerson());
        newOrderItem.setOrderItemStatus(OrderItemStatus.ACTIVE);

        orderItemRepository.save(newOrderItem);
        return "redirect:/order/" + restaurantId;
    }

    @PostMapping("/delete-item/{item_id}/{restaurantId}")
    public String deleteItem(@PathVariable("item_id") int item_id, @PathVariable("restaurantId") int restaurantId) {
        orderItemRepository.deleteById(item_id);
        return "redirect:/order/" + restaurantId;
    }

    @PostMapping("/delete-items/{restaurantId}")
    public String deleteItems(@PathVariable("restaurantId") int restaurantId, @AuthenticationPrincipal PersonDetails personDetails) {
        List<Order_Item> active = orderItemRepository.findAll();
        for (Order_Item o : active) {
            if (o.getPersonId().getId() == personDetails.getPerson().getId()) {
                if (o.getOrderItemStatus() == OrderItemStatus.ACTIVE) {
                    orderItemRepository.delete(o);
                }
            }
        }
        return "redirect:/order/" + restaurantId;
    }

    @PostMapping("/order/{restaurantId}")
    public String order (@PathVariable("restaurantId") int restaurantId, @ModelAttribute("purchase") Purchase purchase,
                         @AuthenticationPrincipal PersonDetails personDetails, Model model, @RequestParam("promo") String promo) {
        Purchase newPurchase = new Purchase();
        Person person = personService.findById(personDetails.getPerson().getId());
        Restaurant restaurant = restaurantService.byId(restaurantId);
        List<Order_Item> activeOrders = orderItemRepository.findOrder_ItemsByOrderItemStatusAndPersonId(OrderItemStatus.ACTIVE, person);
        double allSum = 0;
        List<Integer> biggestPrepTime = new ArrayList<>();
        for (Order_Item o : activeOrders) {
            allSum += o.getPrice();
            biggestPrepTime.add(o.getPrep_time());
        }
        int biggest = Collections.max(biggestPrepTime);

        newPurchase.setPrep_time(biggest);
        newPurchase.setWish(purchase.getWish());
        newPurchase.setRestaurant_id(restaurant);
        newPurchase.setPayment_way(purchase.getPayment_way());
        if (!promo.isEmpty()) {
            Promo_Code promoCode = promo_CodeRepository.findByCode(promo);
            newPurchase.setPromo_code(promoCode);
            newPurchase.setSum(allSum - (allSum * promoCode.getSale() / 100));
        } else {
            newPurchase.setPromo_code(null);
            newPurchase.setSum(allSum);
        }
        newPurchase.setStatus(Status.In_progress);
        newPurchase.setDelivery_way(purchase.getDelivery_way());
        for (Order_Item o : activeOrders) {
            Order_Item byId = orderItemRepository.findById(o.getId()).orElseThrow();
            newPurchase.addOrderItem(byId);
        }
        newPurchase.setPerson_id(person);
        if (purchase.getDelivery_way().equals(Delivery_Way.Delivery)) {
            if (purchase.getAddress().isEmpty()) {
                model.addAttribute("error_message", "Введіть адресу");
                return "ErrorTemplate";
            }
        }
        newPurchase.setAddress(purchase.getAddress());
        newPurchase.setDate(Timestamp.from(Instant.now()));

        purchaseRepository.save(newPurchase);

        if (purchase.getPayment_way().equals(Payment_Way.Card)) {
            int sum = (int) allSum;
            return "redirect:/payment/" + sum;
        }
        int purchase_id = 0;
        for (Order_Item o : activeOrders) {
            purchase_id = o.getPurchaseId().getId();
        }

        MailStructure mail = new MailStructure("Нове замовлення", "Ваше замовлення №" + purchase_id + " вже готується");
        mailService.sendMail(person.getEmail(), mail);

        return "redirect:/my_info";
    }

}
