package com.facundolinlaud.supergame.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.helper.AnimationType;
import com.facundolinlaud.supergame.systems.AnimationSystem;
import com.facundolinlaud.supergame.systems.CameraFocusSystem;
import com.facundolinlaud.supergame.systems.InputMovementSystem;

import java.util.Arrays;

/**
 * Created by facundo on 3/18/16.
 */
public class WorldScreen implements Screen {
    private static final int Z = 0;

    private GameResources gameResources;

    /* Tile map */
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;

    public WorldScreen(GameResources gameResources) {
        this.gameResources = gameResources;

        initializeMap();
        initializePlayer();
        initializeSystems();
    }

    private void initializeSystems() {
        AnimationSystem animationSystem = new AnimationSystem();
        gameResources.engine.addSystem(animationSystem);

        InputMovementSystem inputMovementSystem = new InputMovementSystem();
        gameResources.engine.addSystem(inputMovementSystem);

        CameraFocusSystem cameraFocusSystem = new CameraFocusSystem(camera);
        gameResources.engine.addSystem(cameraFocusSystem);
    }


    private void initializeMap(){
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        map = new TmxMapLoader().load("map/test2.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, gameResources.batch);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
    }

    private void initializePlayer(){
        gameResources.engine.addEntity(new Entity()
                .add(new PositionComponent(0, 0))
                .add(new InputComponent())
                .add(new VelocityComponent(1.9f))
                .add(new RenderComponent())
                .add(new AnimationComponent(Arrays.asList(
                        AnimationType.DOWN, AnimationType.LEFT, AnimationType.RIGHT, AnimationType.UP),
                        new Texture("player/player2.png"))));
    }

    @Override
    public void render(float delta) {
        gameResources.batch.setProjectionMatrix(camera.combined);
        renderer.setView(camera);
        renderer.render(new int[]{0});

        gameResources.batch.begin();
        gameResources.engine.update(delta);
        gameResources.font.draw(gameResources.batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0, 0);
        gameResources.batch.end();

        renderer.render(new int[]{1, 2});
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
