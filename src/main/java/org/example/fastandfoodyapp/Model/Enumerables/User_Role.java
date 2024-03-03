package org.example.fastandfoodyapp.Model.Enumerables;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User_Role {
    private int id;
    private String user_role;

    public User_Role(String user_role) {
        this.user_role = user_role;
    }
}
