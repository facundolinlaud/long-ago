package com.facundolinlaud.supergame.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;

public class MapGraph implements IndexedGraph<Node>{
    protected Array<Node> nodes;

    public MapGraph(Array<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public int getIndex(Node node) {
        return node.getIndex();
    }

    @Override
    public int getNodeCount() {
        return nodes.size;
    }

    @Override
    public Array<Connection<Node>> getConnections(Node fromNode) {
        return fromNode.getConnections();
    }

    public Node getNode(int index){
        return nodes.get(index);
    }
}
