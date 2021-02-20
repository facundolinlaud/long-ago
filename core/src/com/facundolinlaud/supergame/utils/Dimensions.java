package com.facundolinlaud.supergame.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by facundo on 3/20/16.
 */
public class Dimensions {
    public static int SCREEN_WIDTH = 1024;

    public static int SCREEN_HEIGHT = 768;

    /* One meter in pixels */
    public static final float PX_PER_METER = 32f;

    /* Meters to pixels */
    public static final float METERS_PER_PX = 1/32f;

    public static final float BOX_2D_OFFSET = 1/2f;

    public static final float toMeters(int px){
        return (float) px / PX_PER_METER;
    }

    public static final float toMeters(float px){
        return px / PX_PER_METER;
    }

    public static final int toPixels(float meters){
        return (int) (meters * PX_PER_METER);
    }

    /* Cells and Tiles */
    public static final float ONE_CELL = 1;

    public static final Vector2 calculateGlobalPositionInPixelsToMetersRelativeToCenter(float x, float y){
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float relativeX = x - width / 2f;
        float relativeY = y - height / 2f;

        return new Vector2(toMeters(relativeX), toMeters(relativeY));
    }
}
