package org.example.fastandfoodyapp.Services;

import org.example.fastandfoodyapp.Model.DTO.ItemDTO;
import org.example.fastandfoodyapp.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<ItemDTO> itemDTOS() {
        return itemRepository.findAll(Sort.by("id"))
                .stream().map(s->{
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setId(s.getId());
                    itemDTO.setName(s.getItem_name());
                    itemDTO.setCategory(s.getCategory().getDisplayName());
                    itemDTO.setImage(s.getItem_img());
                    itemDTO.setOrder_items(s.getOrder_items());
                    return itemDTO;
                }).collect(Collectors.toList());
    }
}

