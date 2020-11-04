package com.facundolinlaud.supergame.components.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.ai.pathfinding.LinkedGraphPath;
import com.facundolinlaud.supergame.ai.pathfinding.Node;

public class TraverseComponent implements Component {
    private LinkedGraphPath<Node> path;
    private float seekedProximity;
    private Runnable onArrive;

    public TraverseComponent(LinkedGraphPath<Node> path, float seekedProximity, Runnable onArrive) {
        this.path = path;
        this.onArrive = onArrive;
        this.seekedProximity = seekedProximity;
    }

    public void popCell() {
        path.pop();
    }

    public Vector2 getCurrentCell() {
        Node first = path.first();
        return new Vector2(first.getX(), first.getY());
    }

    public Vector2 getNextCell() {
        Node cell = path.get(1);
        return new Vector2(cell.getX(), cell.getY());
    }

    public int getPathLength() {
        return path.getCount();
    }

    public void setPath(LinkedGraphPath<Node> newPath) {
        this.path = newPath;
    }

    public void arrive() {
        onArrive.run();
    }

    public float getSeekedProximity() {
        return seekedProximity;
    }

    public LinkedGraphPath<Node> getPath() {
        return path;
    }
}
