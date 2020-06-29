package com.facundolinlaud.supergame.components.ai;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.ai.pathfinding.LinkedGraphPath;
import com.facundolinlaud.supergame.ai.pathfinding.Node;

import java.awt.*;

public class TraverseComponent implements Component {
    private LinkedGraphPath<Node> path;
    private Runnable onArrive;

    public TraverseComponent(LinkedGraphPath<Node> path) {
        this.path = path;
        this.onArrive = () -> {};
    }

    public TraverseComponent(LinkedGraphPath<Node> path, Runnable onArrive) {
        this.path = path;
        this.onArrive = onArrive;
    }

    public void popCell() {
        path.pop();
    }

    public Point getNextCell() {
        Node first = path.first();
        return new Point(first.getX(), first.getY());
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
}
