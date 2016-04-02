package com.facundolinlaud.supergame.utils;

/**
 * Created by facundo on 3/31/16.
 */
public enum AnimationType {
    SPELL_CAST_UP(7),
    SPELL_CAST_LEFT(7),
    SPELL_CAST_DOWN(7),
    SPELL_CAST_RIGHT(7),
    BLOCKING_UP(8),
    BLOCKING_LEFT(8),
    BLOCKING_DOWN(8),
    BLOCKING_RIGHT(8),
    WALKING_UP(9),
    WALKING_LEFT(9),
    WALKING_DOWN(9),
    WALKING_RIGHT(9),
    SWING_UP(6),
    SWING_LEFT(6),
    SWING_DOWN(6),
    SWING_RIGHT(6),
    SHOOT_UP(13),
    SHOOT_LEFT(13),
    SHOOT_DOWN(13),
    SHOOT_RIGHT(13),
    FALL(6),
    NONE(0);

    private int columns;

    AnimationType(int columns) {
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }

    public static int getMaximumRows(){
        int bigger = values()[0].getColumns();

        for(AnimationType animationType : values()){
            int rows = animationType.columns;

            if(rows > bigger) bigger = rows;
        }

        return bigger;
    }
}
