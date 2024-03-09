package org.example.fastandfoodyapp.Controllers;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.Model.DTO.ItemDTO;
import org.example.fastandfoodyapp.Services.ItemServiceImpl;
import org.example.fastandfoodyapp.Services.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainRestController {

    private ItemService itemService;

    @GetMapping()
    public List<ItemDTO> itemsDto() {
        return itemService.getAllItemDTO();
    }
}
