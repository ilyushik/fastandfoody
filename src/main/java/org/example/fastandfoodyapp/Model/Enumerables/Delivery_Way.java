package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.Getter;

@Getter
public enum Delivery_Way {
    Delivery("Доставка_курєром"),
    PickUp("Самовивіз");

    private final String displayName;

    Delivery_Way(String displayName) {
        this.displayName = displayName;
    }
}
