package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.Action;
import com.facundolinlaud.supergame.model.Direction;

/**
 * Created by facundo on 26/7/16.
 */
public class StatusComponent implements Component {
    public Action action;
    public Direction direction;
    public boolean gathering;

    public StatusComponent() {
        this.action = Action.STANDING;
        this.direction = Direction.RIGHT;
    }
}
