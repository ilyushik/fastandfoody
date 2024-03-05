package org.example.fastandfoodyapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class MainController {
    @GetMapping()
    public String main(@RequestParam(value = "name", required = false,
            defaultValue = "System_User") String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }
}
