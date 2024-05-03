package org.example.fastandfoodyapp.Services;

import lombok.AllArgsConstructor;
import org.example.fastandfoodyapp.Model.DTO.ItemDTO;
import org.example.fastandfoodyapp.Model.Item;
import org.example.fastandfoodyapp.Repositories.ItemRepository;
import org.example.fastandfoodyapp.Services.Service.ItemService;
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
                    itemDTO.setImage("");
                    itemDTO.setPrice(s.getPrice());
                    itemDTO.setDescription(s.getDescription());
                    return itemDTO;
                }).collect(Collectors.toList());
    }

    public Item findItemById(int id) {
        return itemRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id) {
        itemRepository.deleteById(id);
    }

    public List<Item> findByName(String name) {
        return itemRepository.findAll().
                stream().
                filter(i -> i.getItem_name().
                        toLowerCase().
                        contains(name.toLowerCase())).
                collect(Collectors.toList());
    }
}

