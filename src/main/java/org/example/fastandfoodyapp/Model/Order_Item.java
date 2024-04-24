package org.example.fastandfoodyapp.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Order_Item")
public class Order_Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "prep_time", nullable = false)
    private int prep_time;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    private Item item_id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "purchase_id")
    private Purchase purchase_id;

    @Transient
    public String string_image;

    @Transient
    public double sum;

    public Order_Item(int count, int prep_time,
                      Item item_id) {
        this.count = count;
        this.prep_time = prep_time;
        this.item_id = item_id;
    }

    public void setStringImage(String image) {
        this.string_image = image;
    }

    public void setSum(int count, int price) {
        this.sum = count * price;
    }
}
