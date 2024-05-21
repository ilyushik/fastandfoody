package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.Getter;

@Getter
public enum OrderItemStatus {
    ACTIVE("Активно"),
    ORDERED("Замовлено");

    private final String displayName;

    OrderItemStatus(String displayName) {
        this.displayName = displayName;
    }
}
