package org.example.fastandfoodyapp.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.City;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private int id;
    private String address;
    private String email;
    private String phone;
    private City cityId;
    private double longitude;
    private double latitude;

    public RestaurantDTO(String address, String email, String phone, City cityId, double longitude, double latitude) {
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.cityId = cityId;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
