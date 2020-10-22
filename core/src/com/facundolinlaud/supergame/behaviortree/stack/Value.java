package com.facundolinlaud.supergame.behaviortree.stack;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

public class Value {
    private String stringValue;
    private Float floatValue;
    private Entity entityValue;
    private Vector2 positionValue;

    public Value(String stringValue) {
        this.stringValue = stringValue;
    }

    public Value(int intValue) {
        this.floatValue = new Float(intValue);
    }

    public Value(float floatValue) {
        this.floatValue = floatValue;
    }

    public Value(Entity entityValue) {
        this.entityValue = entityValue;
    }

    public Value(Vector2 positionValue) {
        this.positionValue = positionValue;
    }

    public String getString() {
        if (stringValue == null)
            throw new NullValueException();

        return stringValue;
    }

    public Float getFloat() {
        if (floatValue == null)
            throw new NullValueException();

        return floatValue;
    }

    public Entity getEntity() {
        if (entityValue == null)
            throw new NullValueException();

        return entityValue;
    }

    public Vector2 getPosition() {
        if (positionValue == null)
            throw new NullValueException();

        return positionValue;
    }
}
