package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Model.DTO.ItemDTO;
import org.example.fastandfoodyapp.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    private ItemService itemService;

    @GetMapping()
    public List<ItemDTO> itemsDto() {
        return itemService.itemDTOS();
    }
}
