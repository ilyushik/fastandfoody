package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Repositories.Order_ItemRepository;
import org.example.fastandfoodyapp.Repositories.PurchaseRepository;
import org.example.fastandfoodyapp.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private Order_ItemRepository order_itemRepositoryrepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    //http://localhost:8080/main
    @GetMapping()
    public String items(Model model) {
        model.addAttribute("items", itemService.itemDTOS());
        return "main";
    }

    //http://localhost:8080/main/order_item
    @GetMapping("/order_item")
    public String order_items(Model model) {
        model.addAttribute("order_items", order_itemRepositoryrepository.findAll());
        return "order_items";
    }

    //http://localhost:8080/main/purchase
    @GetMapping("/purchase")
    public String purchase(Model model) {
        model.addAttribute("purchases", purchaseRepository.findAll());
        return "purchase";
    }
}
