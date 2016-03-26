package com.facundolinlaud.supergame.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.helper.AnimationType;
import com.facundolinlaud.supergame.helper.BodiesFactory;
import com.facundolinlaud.supergame.managers.MapManager;
import com.facundolinlaud.supergame.managers.PhysicsManager;
import com.facundolinlaud.supergame.managers.UIManager;
import com.facundolinlaud.supergame.systems.*;

import java.util.Arrays;

// * ver si la animacion la puedo encarar por el lado de getLinearVelocity

/**
 * Created by facundo on 3/18/16.
 */
public class WorldScreen implements Screen {
    private static final int Z = 0;
    public static final String PATH_TO_PLAYER_SPRITE = "player/player2.png";
    public static final int VIEWPORT_WIDTH_IN_METERS = 32;

    private GameResources res;

    private MapManager mapManager;
    private PhysicsManager physicsManager;
    private BodiesFactory bodiesFactory;
    private UIManager uiManager;

    public WorldScreen(GameResources res) {
        this.res = res;

        mapManager = new MapManager(res.batch);
        physicsManager = new PhysicsManager(mapManager.getCamera(), mapManager.getMap());
        uiManager = new UIManager();
        bodiesFactory = new BodiesFactory(physicsManager.getWorld());

        initializePlayer();
        initializeSystems();
    }

    private void initializeSystems() {
        res.engine.addSystem(new AnimationSystem());
        res.engine.addSystem(new KeyboardSystem());
        res.engine.addSystem(new MovementSystem());
        res.engine.addSystem(new CameraFocusSystem(mapManager.getCamera()));
        res.engine.addSystem(new PhysicsSystem(physicsManager.getWorld()));

        uiManager.initializeSystems(res.engine);
    }


    private void initializePlayer(){
        res.engine.addEntity(new Entity()
                .add(new PositionComponent(20, 50))
                .add(new InputComponent())
                .add(new KeyboardComponent())
                .add(new VelocityComponent(1.9f))
                .add(new RenderComponent())
                .add(new BodyComponent(bodiesFactory.createDefault()))
                .add(new AnimationComponent(Arrays.asList(
                        AnimationType.DOWN, AnimationType.LEFT, AnimationType.RIGHT, AnimationType.UP),
                        new Texture(PATH_TO_PLAYER_SPRITE)))
                .add(new HealthComponent()));
    }

    @Override
    public void render(float delta) {
        mapManager.renderBaseLayer();

        res.batch.begin();
        res.engine.update(delta);
        res.batch.end();

        mapManager.renderUpperLayer();
        physicsManager.render();
        uiManager.render();
    }

    @Override
    public void resize(int width, int height) {
        mapManager.resize(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        mapManager.dispose();
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
