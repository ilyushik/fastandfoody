package org.example.fastandfoodyapp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Enumerables.User_Role;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(max = 32, message = "Не має перевищувати 32")
    @NotEmpty
    @Column(name = "user_name")
    private String user_name;

    @Size(max = 32, message = "Не має перевищувати 32")
    @NotEmpty
    @Column(name = "surname")
    private String surname;

    @Size(max = 16, message = "Не має перевищувати 16")
    @NotEmpty
    @Column(name = "phone", unique = true)
    private String phone;

    @Size(max = 64)
    @NotEmpty
    @Column(name = "email", unique = true)
    private String email;

    @Size(max = 32, message = "Не має перевищувати 32")
    @NotEmpty
    @Column(name = "username", unique = true)
    private String username;

    @Size(max = 32, message = "Не має перевищувати 32")
    @Size(min = 8, message = "Не має бути менше 8")
    @NotEmpty
    @Column(name = "password")
    private String user_password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role_id")
    @NotEmpty
    private User_Role user_role_id;

    @OneToOne
    private Restaurant restaurant_id;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    private List<Order> orders;

    public User(String user_name, String surname, String phone,
                String email, String username, String user_password,
                User_Role user_role_id, Restaurant restaurant_id) {
        this.user_name = user_name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.user_password = user_password;
        this.user_role_id = user_role_id;
        this.restaurant_id = restaurant_id;
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
