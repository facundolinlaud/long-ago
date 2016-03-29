package com.facundolinlaud.supergame.utils.mediator;

import com.facundolinlaud.supergame.utils.mediator.event.Event;

/**
 * Created by facundo on 3/29/16.
 */
public interface Receiver<T extends Event> {
    void handle(Event event);
}
