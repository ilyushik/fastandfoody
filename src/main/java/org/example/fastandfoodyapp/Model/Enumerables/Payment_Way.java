package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.Getter;

@Getter
public enum Payment_Way {
    Card("Безготівковий розрахунок"),
    Cash("Готівковий розрахунок");

    private final String displayName;

    Payment_Way(String displayName) {
        this.displayName = displayName;
    }
}
