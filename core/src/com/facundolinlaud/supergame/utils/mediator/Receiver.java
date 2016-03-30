package com.facundolinlaud.supergame.utils.mediator;

import com.facundolinlaud.supergame.utils.events.Event;

/**
 * Created by facundo on 3/29/16.
 */
public interface Receiver {
    void handle(Event event);
}
