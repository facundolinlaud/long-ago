package com.facundolinlaud.supergame.components.spawn;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class SpawnedAgentComponent implements Component {
    private Entity spawnLocation;

    public SpawnedAgentComponent(Entity spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public Entity getSpawnLocation() {
        return spawnLocation;
    }
}
