package com.facundolinlaud.supergame.model;

public enum EquipType {
    SPEAR("SPEAR"),
    DAGGER("DAGGER"),
    SWORD("SWORD"),
    BOW("BOW");

    private String name;

    EquipType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static EquipType fromString(String name){
        if(name != null){
            for (EquipType w : EquipType.values()) {
                if (name.endsWith(w.name)) {
                    return w;
                }
            }
        }

        return null;
    }
}
