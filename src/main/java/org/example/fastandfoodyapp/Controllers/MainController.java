package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Mails.MailService;
import org.example.fastandfoodyapp.Mails.MailStructure;
import org.example.fastandfoodyapp.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class MainController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private MailService mailService;

    @GetMapping("/items")
    public String items(Model model) {
        model.addAttribute("items", itemService.itemDTOS());
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
}
