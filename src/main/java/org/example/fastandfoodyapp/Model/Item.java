package org.example.fastandfoodyapp.Model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Enumerables.Category;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String item_name;

    private int price;

    private String description;

    private int prep_time;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

   // private List<Order_Item> order_items;

    public Item(String item_name, int price, String description, int prep_time, Category category_id) {
        this.item_name = item_name;
        this.price = price;
        this.description = description;
        this.prep_time = prep_time;
        this.category = category_id;
    }
}

