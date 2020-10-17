package com.facundolinlaud.supergame.dto;

import com.badlogic.gdx.graphics.Color;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class ColorDto {
    private float red;
    private float green;
    private float blue;
    private float alpha;

    public void setRed(float red) {
        this.red = red;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public Color build() {
        return new Color(red, green, blue, alpha);
    }
}
