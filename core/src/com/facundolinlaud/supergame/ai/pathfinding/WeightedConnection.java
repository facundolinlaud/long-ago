package com.facundolinlaud.supergame.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.DefaultConnection;

public class WeightedConnection<N> extends DefaultConnection<N> {
    private final static float DEFAULT_WEIGHT = 1f;
    private final static float EXPENSIVE_WEIGHT = 4f;

    protected float weight;

    public WeightedConnection(N fromNode, N toNode) {
        super(fromNode, toNode);
        this.weight = DEFAULT_WEIGHT;
    }

    @Override
    public float getCost() {
        return weight;
    }

    public void markAsExpensive() {
        this.weight = EXPENSIVE_WEIGHT;
    }

    public void markAsCheap() {
        this.weight = DEFAULT_WEIGHT;
    }
}
