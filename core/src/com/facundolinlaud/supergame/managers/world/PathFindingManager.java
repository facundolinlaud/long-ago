package com.facundolinlaud.supergame.managers.world;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderAuthority;
import com.facundolinlaud.supergame.ai.pathfinding.PathFinderResult;

public class PathFindingManager {
    private PathFinderAuthority pathFinderAuthority;

    public PathFindingManager(MapManager mapManager, PhysicsManager physicsManager) {
        this.pathFinderAuthority = new PathFinderAuthority(mapManager, physicsManager);
    }

    public PathFinderResult calculate(Vector2 from, Vector2 to) {
        return pathFinderAuthority.searchNodePath(from, to);
    }
}
