package org.example.fastandfoodyapp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Enumerables.Delivery_Way;
import org.example.fastandfoodyapp.Model.Enumerables.Payment_Way;
import org.example.fastandfoodyapp.Model.Enumerables.Status;

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

    //private Restaurant restaurant_id;

    //private Payment_Way payment_way;

    //private Promo_Code promo_code;

    //private Status status;

    //private Delivery_Way delivery_way;

    //private List<Order_Item> order_item_id;

    //private System_User system_user_id;


    public Purchase(int prep_time, String wish, Restaurant restaurant_id,
                    Payment_Way payment_way_id, Promo_Code promo_code_id,
                    Status status_id, Delivery_Way delivery_way_id, System_User systemUser_id) {
        this.prep_time = prep_time;
        this.wish = wish;
//        this.restaurant_id = restaurant_id;
//        this.payment_way = payment_way_id;
//        this.promo_code = promo_code_id;
//        this.status = status_id;
//        this.delivery_way = delivery_way_id;
//        this.system_user_id = systemUser_id;
    }
}
