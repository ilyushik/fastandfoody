package org.example.fastandfoodyapp.GoogleAPI.Maps;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarkerDTO {
    private int id;
    private String address;
    private double longitude;
    private double latitude;

    public MarkerDTO(String address, double longitude, double latitude) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
