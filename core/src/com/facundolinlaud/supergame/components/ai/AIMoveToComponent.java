package com.facundolinlaud.supergame.components.ai;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.facundolinlaud.supergame.ai.pathfinding.LinkedGraphPath;
import com.facundolinlaud.supergame.ai.pathfinding.Node;

import java.awt.*;

public class AIMoveToComponent implements Component {
    private LinkedGraphPath<Node> path;

    public AIMoveToComponent(LinkedGraphPath<Node> path) {
        this.path = path;
    }

    public void popCell(){
        path.pop();
    }

    public Point getNextCell(){
        Node first = path.first();
        return new Point(first.getX(), first.getY());
    }

    public int getPathLength() {
        return path.getCount();
    }

    public void setPath(LinkedGraphPath<Node> newPath){
        this.path = newPath;
    }

    @Override
    public String toString() {
        return "AIMoveToComponent{" +
                "path=" + path +
                '}';
    }
}
