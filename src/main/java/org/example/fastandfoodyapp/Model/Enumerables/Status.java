package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.Getter;

@Getter
public enum Status {
    In_progress("Готується"),
    Delivered("Доставлено"),
    On_way("В дорозі"),
    Canceled("Скасовано");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }
}


