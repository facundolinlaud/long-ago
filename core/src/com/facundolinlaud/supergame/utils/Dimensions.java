package com.facundolinlaud.supergame.utils;

/**
 * Created by facundo on 3/20/16.
 */
public class Dimensions {
    /* One meter in pixels */
    public static final float PX_PER_METER = 32f;

    /* Meters to pixels */
    public static final float METERS_PER_PX = 1/32f;

    public static final float BOX_2D_OFFSET = 1/2f;

    public static final float toMeters(int px){
        return (float) px / METERS_PER_PX;
    }

    public static final int toPixels(int meters){
        return (int) (meters * PX_PER_METER);
    }
}
