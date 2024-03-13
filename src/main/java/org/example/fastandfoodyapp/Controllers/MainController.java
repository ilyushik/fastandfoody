package org.example.fastandfoodyapp.Controllers;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.Services.Service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class MainController {
    private ItemService itemService;

    @GetMapping("/menu")
    public String items(Model model) {
        model.addAttribute("items", itemService.getAllItemDTO());
        return "menu";
    }

    @GetMapping("/map")
    public String maps(Model model) {
        return "map";
    }
}
