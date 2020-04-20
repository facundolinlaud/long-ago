package com.facundolinlaud.supergame.behaviortree.stack;

import com.badlogic.ashley.core.Entity;

public class Value {
    private String stringValue;
    private Float floatValue;
    private Entity entityValue;

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

    public String getStringValue() {
        if(stringValue == null)
            throw new NullValueException();

        return stringValue;
    }

    public Float getFloatValue() {
        if(floatValue == null)
            throw new NullValueException();

        return floatValue;
    }

    public Entity getEntityValue() {
        if(entityValue == null)
            throw new NullValueException();

        return entityValue;
    }
}
