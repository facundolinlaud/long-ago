package com.facundolinlaud.supergame.refactor;

/**
 * Created by facundo on 26/7/16.
 */
public class Status {
    private Action action;
    private Direction direction;

    public Status(String status){
        this.action = Action.fromString(status);
        this.direction = Direction.fromString(status);
    }

    public Status(Action action, Direction direction) {
        this.action = action;
        this.direction = direction;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        if (action != status.action) return false;
        return direction == status.direction;

    }

    @Override
    public int hashCode() {
        int result = action.hashCode();
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Status{" +
                "action=" + action +
                ", direction=" + direction +
                '}';
    }
}
