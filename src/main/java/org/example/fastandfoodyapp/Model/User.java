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
public class User {
    private int id;

    private String user_name;

    private String surname;

    private String phone;

    private String email;

    private String username;

    private String user_password;

    //private User_Role user_role_id;

//    @OneToOne
//    private Restaurant restaurant_id;

    //private List<Order> orders;

    public User(String user_name, String surname, String phone,
                String email, String username, String user_password,
                User_Role user_role_id, Restaurant restaurant_id) {
        this.user_name = user_name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.user_password = user_password;
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
