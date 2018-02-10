package com.facundolinlaud.supergame.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.screens.WorldScreen;

/**
 * Created by facundo on 3/19/16.
 */
public class ScreenManager implements Manager {
    private GameResources gameResources;
    private Screen currentScreen;

    public ScreenManager(GameResources gameResources) {
        this.gameResources = gameResources;
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(Screen currentScreen) {
        if(this.currentScreen != null)
            this.currentScreen.dispose();

        this.currentScreen = currentScreen;
    }

    public void loadWorldScreen(){
        setCurrentScreen(new WorldScreen(gameResources));
    }

    @Override
    public void render() {
        this.currentScreen.render(Gdx.graphics.getDeltaTime());
    }
}
