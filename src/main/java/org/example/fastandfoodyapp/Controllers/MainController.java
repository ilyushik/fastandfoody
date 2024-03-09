package org.example.fastandfoodyapp.Controllers;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.Mails.MailService;
import org.example.fastandfoodyapp.Mails.MailStructure;
import org.example.fastandfoodyapp.Services.ItemServiceImpl;
import org.example.fastandfoodyapp.Services.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
@AllArgsConstructor
public class MainController {

    private ItemService itemService;

    private MailService mailService;

    @GetMapping("/items")
    public String items(Model model) {
        model.addAttribute("items", itemService.getAllItemDTO());
        return "main";
    }

    @GetMapping("/items/{id}")
    public String itemById(@PathVariable("id") int id, Model model,
                           @RequestParam(value = "email", required = false) String email) {
        model.addAttribute("item", itemService.findItemById(id));
        if (email != null) {
            MailStructure mailStructure = new MailStructure("Chosen item",
                    itemService.findItemById(id).getItem_name() + " " + itemService.findItemById(id).getPrice());
            //mailService.sendMail(email, mailStructure);
            mailService.sendMailWithAttachment(email, mailStructure, itemService.findItemById(id).getItem_img());
        }
        return "info";
    }

    @GetMapping("/map")
    public String maps(Model model) {
        return "map";
    }
}
