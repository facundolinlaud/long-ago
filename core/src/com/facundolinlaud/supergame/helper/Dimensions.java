package com.facundolinlaud.supergame.helper;

/**
 * Created by facundo on 3/20/16.
 */
public class Dimensions {
    /* One meter in pixels */
    public static final float ONE_METER_IN_PIXELS = 32f;

    /* Meters to pixels */
    public static final float ONE_PIXEL_IN_METERS = 1/32f;

    public static final float BOX_2D_OFFSET = 1/2f;

    public static final float toMeters(int px){
        return (float) px / ONE_PIXEL_IN_METERS;
    }

    public static final int toPixels(int meters){
        return (int) (meters * ONE_METER_IN_PIXELS);
    }
}
