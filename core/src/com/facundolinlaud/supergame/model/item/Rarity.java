package com.facundolinlaud.supergame.model.item;

public enum Rarity {
    COMMON("COMMON"),
    UNCOMMON("UNCOMMON"),
    RARE("RARE"),
    EPIC("EPIC");

    private String rarity;

    Rarity(String name) {
        this.rarity = name;
    }

    public String getRarity() {
        return rarity;
    }
}
