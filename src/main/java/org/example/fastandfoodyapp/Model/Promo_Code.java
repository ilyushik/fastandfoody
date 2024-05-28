package org.example.fastandfoodyapp.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Promo_Code")
public class Promo_Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(max = 10)
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "sale", nullable = false)
    private int sale;

    @OneToMany(mappedBy = "promo_code", cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    public Promo_Code(String code) {
        this.code = code;
    }
}
