package org.example.fastandfoodyapp.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(max = 32, message = "Не більше 32 символів")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 32, message = "Не більше 32 символів")
    @Column(name = "surname", nullable = false)
    private String surname;

    @Size(max = 16, message = "Не більше 16 символів")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Email
    @Size(max = 64, message = "Не більше 64 символів")
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 32, message = "Не більше 32 символів")
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "person_password", nullable = false)
    @Size(min = 8, message = "Не менше 8 символів")
    @Size(max = 32, message = "Не більше 32 символів")
    private String person_password;

    @Enumerated(EnumType.STRING)
    @Column(name = "person_role", nullable = false)
    private User_Role personRole;

    @OneToOne(mappedBy = "adminId")
    private Restaurant restaurant_id;

    @OneToMany(mappedBy = "person_id", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image", referencedColumnName = "id")
    private Image image;

    @OneToMany(mappedBy = "person_id", cascade = CascadeType.ALL)
    private List<Order_Item> order_items;

    @Transient
    private String view_image;

    public Person(String name, String surname, String phone,
                  String email, String username, String person_password) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.person_password = person_password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", restaurant=" + (restaurant_id != null ? restaurant_id.getId() : null) + // Пример
                '}';
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
