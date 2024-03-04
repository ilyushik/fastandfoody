package org.example.fastandfoodyapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Order;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promo_Code {
    private int id;
    private String code;
    private List<Order> orders;

    public Promo_Code(String code) {
        this.code = code;
    }
}
