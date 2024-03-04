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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 64)
    @Column(name = "item_name", nullable = false)
    private String item_name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "prep_time", nullable = false)
    private int prep_time;

    @Column(name = "category_id")
    @Enumerated(EnumType.STRING)
    private Category category_id;

    @OneToMany(mappedBy = "item_id", cascade = CascadeType.ALL)
    private List<Order_Item> order_items;

    public Item(String item_name, int price, String description, int prep_time, Category category_id) {
        this.item_name = item_name;
        this.price = price;
        this.description = description;
        this.prep_time = prep_time;
        this.category_id = category_id;
    }
}

