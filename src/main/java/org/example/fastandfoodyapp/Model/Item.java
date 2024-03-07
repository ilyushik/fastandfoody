package org.example.fastandfoodyapp.Model;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.Size;
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

    private String item_img;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @OneToMany(mappedBy = "item_id", cascade = CascadeType.ALL)
    private List<Order_Item> order_items;


    public Item(String item_name, int price, String description, int prep_time, String item_img, Category category) {
        this.item_name = item_name;
        this.price = price;
        this.description = description;
        this.prep_time = prep_time;
        this.item_img = item_img;
        this.category = category;
    }
}

