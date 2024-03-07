package org.example.fastandfoodyapp.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Enumerables.User_Role;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String name;

    private String surname;

    private String phone;

    private String email;

    private String username;

    private String person_password;

    @Enumerated(EnumType.STRING)
    @Column(name = "person_role")
    private User_Role person_role;

    @OneToOne(mappedBy = "admin_id")
    private Restaurant restaurant_id;

    @OneToMany(mappedBy = "person_id", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    public Person(String name, String surname, String phone,
                  String email, String username, String person_password) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.person_password = person_password;
    }


    // make @PreAuthorize("hasRole('ROLE_OWNER')")
//    public Restaurant getRestaurant_id() {
//        return restaurant_id;
//    }
//
//    public void setRestaurant_id(Restaurant restaurant_id) {
//        this.restaurant_id = restaurant_id;
//    }
}
