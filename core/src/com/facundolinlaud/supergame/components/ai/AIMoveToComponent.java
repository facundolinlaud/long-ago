package com.facundolinlaud.supergame.components.ai;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.ai.pathfinding.Node;

import java.awt.*;

public class AIMoveToComponent implements Component {
    private Node node;

    public AIMoveToComponent(Node node) {
        this.node = node;
    }

    public Point getPoint(){
        return new Point(node.getX(), node.getY());
    }
}
