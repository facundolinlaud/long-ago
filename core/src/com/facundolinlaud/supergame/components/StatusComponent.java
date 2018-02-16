package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;

/**
 * Created by facundo on 26/7/16.
 */
public class StatusComponent implements Component {
    /* TODO: do I really need to have this logic inside the component? */

    private Action action;
    private Direction direction;
    private boolean changeInActionOrDirection;

    public StatusComponent() {
        this.action = Action.STANDING;
        this.direction = Direction.RIGHT;
        this.changeInActionOrDirection = false;
    }

    public void setAction(Action action){
        changeInActionOrDirection = action != this.action;
        this.action = action;
    }

    public void setDirection(Direction direction) {
        changeInActionOrDirection = action != this.action;
        this.direction = direction;
    }

    public Action getAction() {
        return this.action;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean isChangeInActionOrDirection() {
        return changeInActionOrDirection;
    }

    public void setChangeInActionOrDirection(boolean changeInActionOrDirection) {
        this.changeInActionOrDirection = changeInActionOrDirection;
    }
}
