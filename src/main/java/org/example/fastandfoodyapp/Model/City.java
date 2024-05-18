package org.example.fastandfoodyapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    @Size(max = 32)
    private String name;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @OneToMany(mappedBy = "cityId", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants;

    public City(String name, double longitude, double latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
