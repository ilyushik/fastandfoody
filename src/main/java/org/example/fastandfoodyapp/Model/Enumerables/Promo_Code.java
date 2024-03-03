package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promo_Code {
    private int id;
    private String code;

    public Promo_Code(String code) {
        this.code = code;
    }
}
