package org.example.fastandfoodyapp.Services.Service;

import org.example.fastandfoodyapp.Model.DTO.ItemDTO;
import org.example.fastandfoodyapp.Model.Item;

import java.util.List;

public interface ItemService {
    List<ItemDTO> getAllItemDTO();

    Item findItemById(int id);
}
