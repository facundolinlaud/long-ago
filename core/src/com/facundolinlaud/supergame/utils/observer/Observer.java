package com.facundolinlaud.supergame.utils.observer;

import com.facundolinlaud.supergame.utils.observer.events.Event;

/**
 * Created by facundo on 3/27/16.
 */
public interface Observer<T extends Event> {
    void handle(T event);
}
