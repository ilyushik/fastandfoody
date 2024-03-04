package org.example.fastandfoodyapp.Model.Enumerables;

public enum Category {
    Холодні_напої("Холодні напої"),
    Гарячі_напої("Гарячі напої"),
    Телятина("Телятина"),
    Свинина("Свинина"),
    Риба_та_курка("Риба та курка"),
    Десерти("Десерти"),
    Сніданки("Сніданки"),
    Картопля_фрі_та_соуси("Картопля-фрі та соуси");

    private final String category;
    Category(String category) {
        this.category = category;
    }

}
