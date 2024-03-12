package org.example.fastandfoodyapp.Model;

import javax    .persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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
    @JoinColumn(name = "admin_id", referencedColumnName = "id", nullable = false)
    private Person admin_id;

    @Column(name = "address", nullable = false)
    @Size(max = 64, message = "Не більше 64 символів")
    private String address;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "restaurant_id")
    private List<Purchase> purchases;

    public Restaurant(Person admin_id, String address, double longitude, double latitude,
                      String city, String phone, String email) {
        this.admin_id = admin_id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
        this.phone = phone;
        this.email = email;
    }
}
