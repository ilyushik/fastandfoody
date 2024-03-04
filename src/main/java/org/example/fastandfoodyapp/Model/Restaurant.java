package org.example.fastandfoodyapp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(mappedBy = "restaurant_id", cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", referencedColumnName = "id", unique = true)
    @NotEmpty
    private User admin_id;

    @Size(max = 64, message = "Не має перевищувати 64")
    @NotEmpty
    @Column(name = "address", unique = true)
    private String address;

    @Column(name = "longitude")
    @NotEmpty
    private double longitude;

    @Column(name = "latitude")
    @NotEmpty
    private double latitude;

    @OneToMany(mappedBy = "restaurant_id", cascade = CascadeType.ALL)
    @Column(name = "orders")
    private List<Order> orders;

    public Restaurant(User admin_id, String address, double longitude, double latitude) {
        this.admin_id = admin_id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
