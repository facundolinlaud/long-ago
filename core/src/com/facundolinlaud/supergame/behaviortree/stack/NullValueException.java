package com.facundolinlaud.supergame.behaviortree.stack;

public class NullValueException extends RuntimeException {
    public NullValueException() {
        super("The requested type field is null");
    }
}
