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
}
