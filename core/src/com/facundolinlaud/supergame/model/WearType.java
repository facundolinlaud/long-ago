package com.facundolinlaud.supergame.model;

/**
 * Created by facundo on 3/31/16.
 */
public enum WearType {
    BODY(0, false, false),
    HAIR_BASE(1, false, false),
    HAIR_COLOR(2, false, false),
    EYES(3, false, false),
    HELMET(4, false, true),
    PANTS(5, false, true),
    CHEST(6, false, true),
    GLOVES(7, false, true),
    SHOES(8, false, true),
    WEAPON(9, true, false),
    SHIELD(10, false, true);

    private int renderPriority;
    private boolean hasAttackComponent;
    private boolean hasDefenseComponent;

    WearType(int renderPriority, boolean hasAttackComponent, boolean hasDefenseComponent) {
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
