package com.facundolinlaud.supergame.refactor;

/**
 * Created by facundo on 26/7/16.
 */
public enum Action {
    STANDING("STANDING"),
    SPELL_CASTING("SPELL_CASTING"),
    BLOCKING("BLOCKING"),
    WALKING("WALKING"),
    SWINGING("SWINGING"),
    SHOOTING("SHOOTING"),
    FALLING("FALLING");

    private String name;

    Action(String name) {
        this.name = name;
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
}
