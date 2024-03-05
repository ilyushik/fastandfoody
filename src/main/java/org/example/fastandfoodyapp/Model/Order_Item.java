package org.example.fastandfoodyapp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order_Item {
    private int id;

    private int count;

    private int prep_time;

    //private Item item_id;

    //private Purchase purchase;

    public Order_Item(int count, int prep_time,
                      Item item_id) {
        this.count = count;
        this.prep_time = prep_time;
        //this.item_id = item_id;
    }
}
