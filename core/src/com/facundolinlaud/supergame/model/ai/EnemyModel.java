package com.facundolinlaud.supergame.model.ai;

import com.facundolinlaud.supergame.model.entity.PlayerModel;

public class EnemyModel extends PlayerModel {
    private float viewDistance;

    public float getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(float viewDistance) {
        this.viewDistance = viewDistance;
    }
}
