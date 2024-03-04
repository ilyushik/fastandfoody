package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.Getter;

@Getter
public enum Category {
    Холодні_напої("Холодні напої"),
    Гарячі_напої("Гарячі напої"),
    Телятина("Телятина"),
    Свинина("Свинина"),
    Риба_та_курка("Риба та курка"),
    Десерти("Десерти"),
    Сніданки("Сніданки"),
    Картопля_фрі_та_соуси("Картопля-фрі та соуси");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

}
