package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.Getter;

@Getter
public enum Status {
    Готується("Готується"),
    Доставлено("Доставлено"),
    В_дорозі("В дорозі");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }
}


