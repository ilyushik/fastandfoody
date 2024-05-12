package org.example.fastandfoodyapp.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Enumerables.Delivery_Way;
import org.example.fastandfoodyapp.Model.Enumerables.Payment_Way;
import org.example.fastandfoodyapp.Model.Enumerables.Status;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "prep_time", nullable = false)
    private int prep_time;

    @Column(name = "wish")
    private String wish;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    private Restaurant restaurant_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_way", nullable = false)
    private Payment_Way payment_way;

    @ManyToOne
    @JoinColumn(name = "promo_code", referencedColumnName = "id")
    private Promo_Code promo_code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_way", nullable = false)
    private Delivery_Way delivery_way;

    @OneToMany(mappedBy = "purchase_id",cascade = CascadeType.ALL)
    private List<Order_Item> order_item_id;

    @Transient
    private double price;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person_id;

    @Column(name = "address")
    private String address;

    @Column(name = "date", nullable = false)
    private Timestamp date;

    // useless field
    @Column(name = "sum", nullable = false)
    private double sum;


    public Purchase(int prep_time, String wish, String address, Timestamp date,
                    Restaurant restaurant_id, Payment_Way payment_way, Promo_Code promo_code,
                    Status status, Delivery_Way delivery_way, Person person_id) {
        this.prep_time = prep_time;
        this.wish = wish;
        this.address = address;
        this.date = date;
        this.restaurant_id = restaurant_id;
        this.payment_way = payment_way;
        this.promo_code = promo_code;
        this.status = status;
        this.delivery_way = delivery_way;
        this.person_id = person_id;
    }

    public void setPrice(List<Order_Item> order_items) {
        for (Order_Item o : order_items) {
            this.price += o.getItem_id().getPrice() * o.getCount();
        }
    }
}
