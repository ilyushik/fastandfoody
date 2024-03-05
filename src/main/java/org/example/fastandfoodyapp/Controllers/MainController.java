package org.example.fastandfoodyapp.Controllers;

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

    @GetMapping()
    public String items(Model model) {
        model.addAttribute("items", itemService.itemDTOS());
        return "main";
    }
}
