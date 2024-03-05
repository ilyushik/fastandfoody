package org.example.fastandfoodyapp.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fastandfoodyapp.Model.Enumerables.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private int id;
    private String name;
    private String category;

    public ItemDTO(String name, String category) {
        this.name = name;
        this.category = category;
    }
}
