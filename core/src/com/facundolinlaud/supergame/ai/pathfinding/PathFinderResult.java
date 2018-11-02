package com.facundolinlaud.supergame.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.GraphPath;

public class PathFinderResult {
    private GraphPath<Node> path;
    private boolean found;

    public PathFinderResult(GraphPath<Node> path, boolean found) {
        this.path = path;
        this.found = found;
    }

    public GraphPath<Node> getPath() {
        return path;
    }

    public boolean isFound() {
        return found;
    }
}
