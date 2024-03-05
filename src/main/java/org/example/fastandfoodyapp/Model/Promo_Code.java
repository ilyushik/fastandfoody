package org.example.fastandfoodyapp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promo_Code {
    private int id;

    private String code;

    //private List<Purchase> purchases;

    public Promo_Code(String code) {
        this.code = code;
    }
}
