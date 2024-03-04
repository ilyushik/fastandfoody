package org.example.fastandfoodyapp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Promo_Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code", unique = true)
    @NotNull
    private String code;

    @OneToMany(mappedBy = "promo_code_id", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Promo_Code(String code) {
        this.code = code;
    }
}
