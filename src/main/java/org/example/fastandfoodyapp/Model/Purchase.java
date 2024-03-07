package org.example.fastandfoodyapp.Model;

import javax.persistence.*;
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
@Table(name = "Purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private int prep_time;

    private String wish;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_way")
    private Payment_Way payment_way;

    @ManyToOne
    @JoinColumn(name = "promo_code", referencedColumnName = "id")
    private Promo_Code promo_code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_way")
    private Delivery_Way delivery_way;

    @OneToMany(mappedBy = "purchase_id",cascade = CascadeType.ALL)
    private List<Order_Item> order_item_id;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person_id;


    public Purchase(int prep_time, String wish, Restaurant restaurant_id,
                    Payment_Way payment_way_id, Promo_Code promo_code_id,
                    Status status_id, Delivery_Way delivery_way_id, Person person_id) {
        this.prep_time = prep_time;
        this.wish = wish;
        this.restaurant_id = restaurant_id;
        this.payment_way = payment_way_id;
        this.promo_code = promo_code_id;
        this.status = status_id;
        this.delivery_way = delivery_way_id;
        this.person_id = person_id;
    }
}
