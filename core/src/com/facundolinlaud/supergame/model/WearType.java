package com.facundolinlaud.supergame.model;

/**
 * Created by facundo on 3/31/16.
 */
public enum WearType {
    BODY(0, false, false),
    HAIR(1, false, false),
    EYES(2, false, false),
    HELMET(3, false, true),
    PANTS(4, false, true),
    CHEST(5, false, true),
    GLOVES(6, false, true),
    SHOES(7, false, true),
    WEAPON(8, true, false),
    SHIELD(9, false, true);

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
