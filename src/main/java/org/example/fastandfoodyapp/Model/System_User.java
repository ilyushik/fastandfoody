package org.example.fastandfoodyapp.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Enumerables.User_Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "System_User")
public class System_User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String system_user_name;

    private String surname;

    private String phone;

    private String email;

    private String system_username;

    private String system_user_password;

    //private User_Role system_user_role_id;

//    @OneToOne
//    private Restaurant restaurant_id;

    //private List<Purchase> orders;

    public System_User(String user_name, String surname, String phone,
                       String email, String username, String user_password,
                       User_Role user_role_id, Restaurant restaurant_id) {
        this.system_user_name = user_name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.system_username = username;
        this.system_user_password = user_password;
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
