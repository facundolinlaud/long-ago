package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.managers.world.PathFindingManager;
import com.facundolinlaud.supergame.utils.Mappers;

public class NodeOccupationSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private PathFindingManager pathFindingManager;

    public NodeOccupationSystem(PathFindingManager pathFindingManager) {
        super(Family.all(BodyComponent.class, PositionComponent.class).get());
        this.pathFindingManager = pathFindingManager;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = pm.get(entity);
        Vector2 position = positionComponent.getPosition();
        pathFindingManager.occupy(entity, position);
    }
}
