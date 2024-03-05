package org.example.fastandfoodyapp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
public class Order {
    private int id;

    private int prep_time;

    private String wish;

    //private Restaurant restaurant_id;

    //private Payment_Way payment_way_id;

    //private Promo_Code promo_code_id;

    //private Status status_id;

    //private Delivery_Way delivery_way_id;

    //private List<Order_Item> order_item_id;

    //private User user_id;


    public Order(int prep_time, String wish, Restaurant restaurant_id,
                 Payment_Way payment_way_id, Promo_Code promo_code_id,
                 Status status_id, Delivery_Way delivery_way_id, User user_id) {
        this.prep_time = prep_time;
        this.wish = wish;
//        this.restaurant_id = restaurant_id;
//        this.payment_way_id = payment_way_id;
//        this.promo_code_id = promo_code_id;
//        this.status_id = status_id;
//        this.delivery_way_id = delivery_way_id;
//        this.user_id = user_id;
    }
}
