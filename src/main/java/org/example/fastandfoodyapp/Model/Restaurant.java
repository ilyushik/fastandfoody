package org.example.fastandfoodyapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private int id;

    //private System_User admin_id;

    private String address;

    private double longitude;

    private double latitude;

    //private List<Purchase> purchases;

    public Restaurant(System_User admin_id, String address, double longitude, double latitude) {
        //this.admin_id = admin_id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
