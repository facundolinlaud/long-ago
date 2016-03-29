package com.facundolinlaud.supergame.utils.observer;


import com.facundolinlaud.supergame.utils.events.Event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 3/27/16.
 */
public class Observable {
    private Map<Class<? extends Event>, Observer> observers;

    public Observable() {
        this.observers = new HashMap<>();
    }

    public void addObserver(Class<? extends Event> eventToHandle, Observer<?> observer){
        this.observers.put(eventToHandle, observer);
    }

    public void notifyObservers(Class<? extends Event> clazz, Event event){
        observers.entrySet().stream().filter(p -> p.getKey().equals(clazz)).forEach(o -> o.getValue().handle(event));
    }
}
