package org.example.fastandfoodyapp.Model.Enumerables;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Order;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment_Way {
    private int id;
    private String way;
    private List<Order> orders;

    public Payment_Way(String way) {
        this.way = way;
    }
}
