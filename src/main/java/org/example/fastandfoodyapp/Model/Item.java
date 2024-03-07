package org.example.fastandfoodyapp.Model;

import javax.persistence.*;
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

    @Column(name = "item_name", nullable = false)
    @Size(max = 64, message = "Не більше 64 символів")
    private String item_name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "prep_time", nullable = false)
    private int prep_time;

    @Column(name = "item_img", nullable = false)
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

