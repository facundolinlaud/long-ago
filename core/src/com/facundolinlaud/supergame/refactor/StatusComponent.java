package com.facundolinlaud.supergame.refactor;

import com.badlogic.ashley.core.Component;

/**
 * Created by facundo on 26/7/16.
 */
public class StatusComponent implements Component {
    public Action action;
    public Direction direction;

    public StatusComponent() {}
}
