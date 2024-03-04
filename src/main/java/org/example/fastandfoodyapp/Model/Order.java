package org.example.fastandfoodyapp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Enumerables.Delivery_Way;
import org.example.fastandfoodyapp.Model.Enumerables.Payment_Way;
import org.example.fastandfoodyapp.Model.Enumerables.Status;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "prep_time")
    private int prep_time;

    @Column(name = "wish")
    private String wish;

    @ManyToOne
    @JoinColumn(name = "orders", referencedColumnName = "id")
    private Restaurant restaurant_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_way_id")
    private Payment_Way payment_way_id;

    @ManyToOne
    @JoinColumn(name = "promo_code_id", referencedColumnName = "id")
    private Promo_Code promo_code_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_id")
    private Status status_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_way_id")
    private Delivery_Way delivery_way_id;

    @OneToMany(mappedBy = "item_id", cascade = CascadeType.ALL)
    @Column(name = "order_item_id")
    private List<Order_Item> order_item_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "orders")
    private User user_id;


    public Order(int prep_time, String wish, Restaurant restaurant_id,
                 Payment_Way payment_way_id, Promo_Code promo_code_id,
                 Status status_id, Delivery_Way delivery_way_id, User user_id) {
        this.prep_time = prep_time;
        this.wish = wish;
        this.restaurant_id = restaurant_id;
        this.payment_way_id = payment_way_id;
        this.promo_code_id = promo_code_id;
        this.status_id = status_id;
        this.delivery_way_id = delivery_way_id;
        this.user_id = user_id;
    }
}
