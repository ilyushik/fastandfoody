package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Model.Enumerables.User_Role;
import org.example.fastandfoodyapp.Model.Person;
import org.example.fastandfoodyapp.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private PersonService personService;

    @GetMapping("")
    public String mainOwner() {
        return "owner/main";
    }

    @GetMapping("/admins")
    public String pageWithAdmins(Model model) {
        List<Person> admins = personService.findByRole(User_Role.ROLE_ADMIN);
        model.addAttribute("admins", admins);
        return "owner/admins";
    }
}
