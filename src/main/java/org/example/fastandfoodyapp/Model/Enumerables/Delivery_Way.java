package org.example.fastandfoodyapp.Model.Enumerables;

public enum Delivery_Way {

    Доставка_курєром("Доставка_курєром"),

    Самовивіз("Самовивіз");
    private final String way;

    Delivery_Way(String way) {
        this.way = way;
    }
}
