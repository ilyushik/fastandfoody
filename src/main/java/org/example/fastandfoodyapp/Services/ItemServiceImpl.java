package org.example.fastandfoodyapp.Services;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.Model.DTO.ItemDTO;
import org.example.fastandfoodyapp.Model.Item;
import org.example.fastandfoodyapp.Repositories.ItemRepository;
import org.example.fastandfoodyapp.Services.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public List<ItemDTO> getAllItemDTO() {
        return itemRepository.findAll(Sort.by("id"))
                .stream().map(s->{
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setId(s.getId());
                    itemDTO.setName(s.getItem_name());
                    itemDTO.setCategory(s.getCategory().getDisplayName());
                    itemDTO.setImage(s.getItem_img());
                    itemDTO.setPrice(s.getPrice());
                    return itemDTO;
                }).collect(Collectors.toList());
    }

    public Item findItemById(int id) {
        return itemRepository.findById(id).orElseThrow();
    }
}

