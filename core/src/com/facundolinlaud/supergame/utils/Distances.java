package com.facundolinlaud.supergame.utils;

import com.badlogic.gdx.math.Vector2;

public class Distances {
    public static float calculateEuclideanDistanceBetween(Vector2 a, Vector2 b){
        return (float) Math.abs(Math.sqrt(Math.pow(a.x, 2) + Math.pow(a.y, 2)) - Math.sqrt(Math.pow(b.x, 2) + Math.pow(b.y, 2)));
    }
}
