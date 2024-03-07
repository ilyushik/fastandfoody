package org.example.fastandfoodyapp.Model;

import javax    .persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Person admin_id;

    private String address;

    private double longitude;

    private double latitude;

    @OneToMany(mappedBy = "restaurant_id")
    private List<Purchase> purchases;

    public Restaurant(Person admin_id, String address, double longitude, double latitude) {
        this.admin_id = admin_id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
