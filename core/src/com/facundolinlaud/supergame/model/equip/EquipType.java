package com.facundolinlaud.supergame.model.equip;

public enum EquipType {
    SPEAR("SPEAR"),
    DAGGER("DAGGER"),
    SWORD("SWORD"),
    BOW("BOW"),
    WAND("WAND"),
    NOT_IMPORTANT("NOT_IMPORTANT");

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
