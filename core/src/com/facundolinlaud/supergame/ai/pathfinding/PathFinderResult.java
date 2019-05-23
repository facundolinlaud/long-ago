package com.facundolinlaud.supergame.ai.pathfinding;

public class PathFinderResult {
    private LinkedGraphPath<Node> path;
    private boolean found;

    public PathFinderResult(LinkedGraphPath<Node> path, boolean found) {
        this.path = path;
        this.found = found;
    }

    public LinkedGraphPath<Node> getPath() {
        return path;
    }

    public boolean isFound() {
        return found;
    }
}
