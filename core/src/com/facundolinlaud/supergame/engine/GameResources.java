package com.facundolinlaud.supergame.engine;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by facundo on 3/18/16.
 */
public class GameResources {
    public Engine engine;
    public SpriteBatch batch;
    public BitmapFont font;

    public GameResources(Engine engine, SpriteBatch batch, BitmapFont font) {
        this.engine = engine;
        this.batch = batch;
        this.font = font;
    }
}
