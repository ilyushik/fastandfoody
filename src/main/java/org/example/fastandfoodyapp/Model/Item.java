package org.example.fastandfoodyapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Enumerables.Category;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private int id;
    private String item_name;
    private int price;
    private String description;
    private int prep_time;
    private Category category_id;
    private List<Order_Item> order_items;

    public Item(String item_name, int price, String description, int prep_time, Category category_id) {
        this.item_name = item_name;
        this.price = price;
        this.description = description;
        this.prep_time = prep_time;
        this.category_id = category_id;
    }
}

