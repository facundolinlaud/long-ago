package com.facundolinlaud.supergame.utils;

import com.badlogic.gdx.math.Vector2;

public class Distances {
    public static float calculateEuclideanDistanceBetween(Vector2 a, Vector2 b){
        float x = Math.abs(a.x - b.x);
        float y = Math.abs(a.y - b.y);

        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
