package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.Getter;

@Getter
public enum Delivery_Way {
    Доставка_курєром("Доставка_курєром"),
    Самовивіз("Самовивіз");

    private final String displayName;

    Delivery_Way(String displayName) {
        this.displayName = displayName;
    }
}
