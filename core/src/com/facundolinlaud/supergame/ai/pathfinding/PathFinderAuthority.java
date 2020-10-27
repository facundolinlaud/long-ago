package com.facundolinlaud.supergame.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.managers.world.MapManager;
import com.facundolinlaud.supergame.managers.world.PhysicsManager;

public class PathFinderAuthority {
    public static final String HEIGHT = "height";

    private PathFinder<Node> pathfinder;
    private MapGraphCreator mapGraphCreator;
    private MapGraph mapGraph;

    private int tiledMapRows;

    public PathFinderAuthority(MapManager mapManager, PhysicsManager physicsManager) {
        this.mapGraphCreator = new MapGraphCreator(mapManager.getMap(), physicsManager.getObstacles());
        this.mapGraph = mapGraphCreator.createMapGraphFromTiledMap();
        this.pathfinder = new IndexedAStarPathFinder<>(mapGraph);

        MapProperties prop = mapManager.getMap().getProperties();
        this.tiledMapRows = prop.get(HEIGHT, Integer.class);
    }

    public PathFinderResult searchNodePath(Vector2 from, Vector2 to) {
        LinkedGraphPath<Node> outPath = new LinkedGraphPath();

        int fromNodeIndex = resolveNodeIndex(from);
        int toNodeIndex = resolveNodeIndex(to);

        Node fromNode = mapGraph.getNode(fromNodeIndex);
        Node toNode = mapGraph.getNode(toNodeIndex);

        boolean pathFound = pathfinder.searchNodePath(fromNode, toNode, new ManhattanDistance(), outPath);

        // el problema es que para que el agente pueda doblar precisament, la stanidng cell se tiene que eliminar
        // solo si realmente el agente esta sobre el medio de la cell, sino no.
        // como el pursue system va tickeando en todos los ticks y calcula desde el ((int) pos.x, (int) pos.y) entonces
        // no importa que tan lejos o cerca estas realmente del centro de la cell, siempre desestima la standing cell
        // apenas se toca
        if (pathFound) {
            removeStandingCell(outPath);
        }

        return new PathFinderResult(outPath, pathFound);
    }

    private void removeStandingCell(LinkedGraphPath outPath) {
        outPath.pop();
    }

    private int resolveNodeIndex(Vector2 vector) {
        int x = (int) vector.x;
        int y = (int) vector.y;

        return x + y * tiledMapRows;
    }

    public void free(Vector2 oldPosition) {
        int index = resolveNodeIndex(oldPosition);
        Node node = mapGraph.getNode(index);
        node.getConnections().forEach(c -> {
            // Try to remove this casting some day
            WeightedConnection wc = (WeightedConnection) c;
            wc.markAsCheap();
        });
    }

    public void occupy(Vector2 position) {
        int index = resolveNodeIndex(position);
        Node node = mapGraph.getNode(index);
        node.getConnections().forEach(c -> {
            // Try to remove this casting some day
            WeightedConnection wc = (WeightedConnection) c;
            wc.markAsExpensive();
        });
    }
}
