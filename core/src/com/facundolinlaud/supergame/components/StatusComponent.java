package com.facundolinlaud.supergame.components;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;

/**
 * Created by facundo on 26/7/16.
 */
public class StatusComponent implements Component {
    /* TODO: do I really need to have this logic inside the component? */

    private Action previousAction;
    private Direction previousDirection;

    private Action action;
    private Direction direction;

    public StatusComponent() {
        this.action = Action.STANDING;
        this.direction = Direction.RIGHT;
    }

    public void setAction(Action action){
        this.previousAction = this.action;
        this.action = action;
    }

    public void setDirection(Direction direction) {
        this.previousDirection = this.direction;
        this.direction = direction;
    }

    public Action getAction() {
        return this.action;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Action getPreviousAction() {
        return previousAction;
    }

    public Direction getPreviousDirection() {
        return previousDirection;
    }

    public void syncPreviousStatus() {
        this.previousAction = this.action;
        this.previousDirection = this.direction;
    }
}
