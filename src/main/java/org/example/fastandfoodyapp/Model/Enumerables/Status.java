package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Order;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    private int id;
    private String status_name;
    private List<Order> orders;

    public Status(String status_name) {
        this.status_name = status_name;
    }
}
