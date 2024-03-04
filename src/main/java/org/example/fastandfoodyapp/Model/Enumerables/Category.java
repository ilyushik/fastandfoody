package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Item;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int id;
    private String category_name;
    private List<Item> items;

    public Category(String category_name) {
        this.category_name = category_name;
    }
}
