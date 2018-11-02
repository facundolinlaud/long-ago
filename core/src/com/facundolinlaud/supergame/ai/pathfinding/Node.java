package com.facundolinlaud.supergame.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

public class Node {
    private final int index;
    private final int x;
    private final int y;
    private final Array<Connection<Node>> connections;

    public Node (final int index, final int x, final int y, final int capacity) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.connections = new Array<>(capacity);
    }

    public int getIndex() {
        return index;
    }

    public Array<Connection<Node>> getConnections() {
        return connections;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
