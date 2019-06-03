package com.facundolinlaud.supergame.model.equip;

/**
 * Created by facundo on 3/31/16.
 */
public enum EquipSlot {
    SHADOW(4, false, false),
    BODY(5, false, false),
    HAIR_BASE(6, false, false),
    HAIR_COLOR(7, false, false),
    EYES(8, false, false),
    HELMET(9, false, true),
    PANTS(10, false, true),
    BELT(11, false, true),
    CHEST(12, false, true),
    GLOVES(13, false, true),
    SHOES(14, false, true),
    WEAPON(15, true, false),
    SHIELD(16, false, true),
    BOW(17, true, false);

    private int renderPriority;
    private boolean hasAttackComponent;
    private boolean hasDefenseComponent;

    EquipSlot(int renderPriority, boolean hasAttackComponent, boolean hasDefenseComponent) {
        this.renderPriority = renderPriority;
        this.hasAttackComponent = hasAttackComponent;
        this.hasDefenseComponent = hasDefenseComponent;
    }

    public int getRenderPriority() {
        return renderPriority;
    }

    public boolean hasAttackComponent() {
        return hasAttackComponent;
    }

    public boolean hasDefenseComponent() {
        return hasDefenseComponent;
    }
}
