package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Delivery_Way {
    private int id;
    private String way;

    public Delivery_Way(String way) {
        this.way = way;
    }
}
