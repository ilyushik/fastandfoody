package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public String items(Model model) {
        model.addAttribute("items", itemService.itemDTOS());
        return "main";
    }

    @GetMapping("/items/{id}")
    public String itemById(@PathVariable("id") int id, Model model) {
        model.addAttribute("item", itemService.findItemById(id));
        return "info";
    }

    @GetMapping("/map")
    public String maps(Model model) {
        return "map";
    }
}
