package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderAuthority;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderResult;

import java.util.HashMap;
import java.util.Map;

public class PathFindingManager implements EntityListener {
    private PathFinderAuthority pathFinderAuthority;
    private Map<Entity, Vector2> occupation;

    public PathFindingManager(MapManager mapManager, PhysicsManager physicsManager) {
        this.pathFinderAuthority = new PathFinderAuthority(mapManager, physicsManager);
        this.occupation = new HashMap();
    }

    public PathFinderResult calculate(Vector2 from, Vector2 to) {
        return pathFinderAuthority.searchNodePath(from, to);
    }

    public void occupy(Entity entity, Vector2 position) {
        Vector2 roundedPosition = roundPosition(position);

        if (occupation.containsKey(entity)) {
            Vector2 oldPosition = occupation.get(entity);

            if (!oldPosition.equals(roundedPosition)) {
                pathFinderAuthority.free(roundedPosition);
            }
        }

        pathFinderAuthority.occupy(roundedPosition);
        occupation.put(entity, roundedPosition);
    }

    private void free(Entity entity) {
        if (occupation.containsKey(entity)) {
            Vector2 position = occupation.remove(entity);
            pathFinderAuthority.free(position);
        }
    }

    @Override
    public void entityAdded(Entity entity) {
    }

    @Override
    public void entityRemoved(Entity entity) {
        free(entity);
    }

    private Vector2 roundPosition(Vector2 position) {
        return new Vector2(Math.round(position.x), Math.round((position.y)));
    }
}
