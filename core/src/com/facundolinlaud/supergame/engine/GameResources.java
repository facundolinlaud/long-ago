package com.facundolinlaud.supergame.engine;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.facundolinlaud.supergame.managers.FontManager;

/**
 * Created by facundo on 3/18/16.
 */
public class GameResources {
    public Engine engine;
    public SpriteBatch batch;

    public GameResources(Engine engine, SpriteBatch batch) {
        this.engine = engine;
        this.batch = batch;
    }
}
