package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.spawn.SpawnLocationComponent;
import com.facundolinlaud.supergame.model.spawn.SpawnLocation;

import java.util.List;

public class SpawnManager {
    public SpawnManager(Engine engine, List<SpawnLocation> locations) {
        /* Each spawn point is an entity */
        for(SpawnLocation spawnLocation: locations){
            Entity e = new Entity().add(new SpawnLocationComponent(spawnLocation));
//            engine.addEntity(e);
        }
    }
}
