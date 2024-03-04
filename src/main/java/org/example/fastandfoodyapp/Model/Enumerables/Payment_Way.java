package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.Getter;

@Getter
public enum Payment_Way {
    Безготівковий_розрахунок("Безготівковий розрахунок"),
    Готівковий_розрахунок("Готівковий розрахунок");

    private final String displayName;

    Payment_Way(String displayName) {
        this.displayName = displayName;
    }
}
