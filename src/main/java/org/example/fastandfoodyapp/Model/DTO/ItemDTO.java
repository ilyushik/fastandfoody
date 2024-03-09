package org.example.fastandfoodyapp.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Enumerables.Category;
import org.example.fastandfoodyapp.Model.Order_Item;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private int id;
    private String name;
    private String category;
    private String image;
    private int price;

    public ItemDTO(String name, String category, String image, int price) {
        this.name = name;
        this.category = category;
        this.image = image;
        this.price = price;
    }
}
