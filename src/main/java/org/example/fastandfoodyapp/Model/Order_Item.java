package org.example.fastandfoodyapp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "prep_time", nullable = false)
    private int prep_time;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item_id;

    @ManyToOne
    @JoinColumn(name = "order", referencedColumnName = "id")
    private Order order;

    public Order_Item(int count, int prep_time,
                      Item item_id) {
        this.count = count;
        this.prep_time = prep_time;
        this.item_id = item_id;
    }
}
