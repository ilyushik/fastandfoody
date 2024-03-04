package org.example.fastandfoodyapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private int id;
    private User admin_id;
    private String address;
    private double longitude;
    private double latitude;
    private List<Order> orders;

    public Restaurant(User admin_id, String address, double longitude, double latitude) {
        this.admin_id = admin_id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
