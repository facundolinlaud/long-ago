package com.facundolinlaud.supergame.utils.mediator;


import com.facundolinlaud.supergame.utils.events.Event;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 3/29/16.
 */
public class Mediator {
    private Map<Class<? extends Event>, Receiver> receivers;

    public Mediator() {
        this.receivers = new HashMap<>();
    }

    public void subscribe(Class clazz, Receiver receiver){
        this.receivers.put(clazz, receiver);
    }

    public void raise(Messenger messenger, Class clazz, Event event){
        receivers.entrySet().stream()
                .filter(entry -> entry.getKey().equals(clazz) && !entry.getValue().equals(messenger))
                .forEach(entry -> entry.getValue().handle(event));
    }
}
