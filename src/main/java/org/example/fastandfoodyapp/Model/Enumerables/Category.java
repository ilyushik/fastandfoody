package org.example.fastandfoodyapp.Model.Enumerables;

import lombok.Getter;

@Getter
public enum Category {
    Cold_drinks("Холодні напої"),
    Hot_drinks("Гарячі напої"),
    Beef("Телятина"),
    Pork("Свинина"),
    Fish_and_chicken("Риба та курка"),
    Desserts("Десерти"),
    Breakfasts("Сніданки"),
    Fries_and_sauces("Картопля-фрі та соуси");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

}
