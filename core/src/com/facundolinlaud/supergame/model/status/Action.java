package com.facundolinlaud.supergame.model.status;

/**
 * Created by facundo on 26/7/16.
 */
public enum Action {
    STANDING("STANDING"),
    WALKING("WALKING"),

    SPELL_PRECASTING("SPELL_PRECASTING"),
    SPELL_CASTING("SPELL_CASTING"),
    RANGE_CASTING("RANGE_CASTING"),

    BLOCKING("BLOCKING"),
    SWINGING("SWINGING"),

    MELEE_DASH_CASTING("MELEE_DASH_CASTING"),
    SWING_CASTING("SWING_CASTING"),
    DASHING("DASHING"),

    AIMING("AIMING"),
    PRESHOOTING("PRESHOOTING"),
    SHOOTING("SHOOTING"),

    FALLING("FALLING");

    private String name;

    Action(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Action fromString(String name){
        if(name != null){
            for (Action a : Action.values()) {
                if (name.startsWith(a.name)) {
                    return a;
                }
            }
        }

        return null;
    }

    public boolean isBusy(){
        return !(this == STANDING || this == WALKING);
    }

    public boolean isCasting() {
        return this == SPELL_CASTING || this == RANGE_CASTING ||
                this == MELEE_DASH_CASTING || this == SPELL_PRECASTING || this == SWING_CASTING;
    }
}
