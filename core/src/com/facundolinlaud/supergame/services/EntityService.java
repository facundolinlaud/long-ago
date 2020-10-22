package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.Entity;

public class EntityService extends Service {
    public Entity create(){
        Entity e = new Entity();
        getEngine().addEntity(e);

        return e;
    }
}
