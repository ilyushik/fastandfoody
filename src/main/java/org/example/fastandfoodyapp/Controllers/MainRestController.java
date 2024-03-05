package org.example.fastandfoodyapp.Controllers;

import org.example.fastandfoodyapp.Model.DTO.ItemDTO;
import org.example.fastandfoodyapp.Model.Enumerables.Category;
import org.example.fastandfoodyapp.Model.Item;
import org.example.fastandfoodyapp.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainRestController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping()
    public List<ItemDTO> itemsDto() {
        String name = Category.Cold_drinks.getDisplayName();
        return itemRepository.findAll(Sort.by("id"))
                .stream().map(s->{
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setId(s.getId());
            itemDTO.setName(s.getItem_name());
            itemDTO.setCategory(s.getCategory().getDisplayName());
            return itemDTO;
        }).collect(Collectors.toList());
    }
}
