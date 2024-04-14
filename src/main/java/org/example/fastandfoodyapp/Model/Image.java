package org.example.fastandfoodyapp.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @Lob
    @Column(name = "imageData", length = 10000000)
    private byte[] imageData;

    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)
    private List<Person> person;

    @OneToOne(mappedBy = "image")
    private Item item;
}
