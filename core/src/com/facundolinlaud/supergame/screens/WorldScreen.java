package com.facundolinlaud.supergame.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.facundolinlaud.supergame.components.InputComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.VelocityComponent;
import com.facundolinlaud.supergame.engine.GameResources;

/**
 * Created by facundo on 3/18/16.
 */
public class WorldScreen implements Screen {
    private static final int Z = 0;

    private GameResources gameResources;

    private PositionComponent playerPositionComponent;

    /* Tile map */
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;

    public WorldScreen(GameResources gameResources) {
        this.gameResources = gameResources;

        initializeMap();
        initializePlayer();
    }

    private void initializeMap(){
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        map = new TmxMapLoader().load("map/test1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, gameResources.batch);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
    }

    private void initializePlayer(){
        playerPositionComponent = new PositionComponent(0, 0);

        gameResources.engine.addEntity(new Entity()
                .add(playerPositionComponent)
                .add(new InputComponent())
                .add(new VelocityComponent(2f))
                .add(new RenderComponent(new Texture("player/player1.png"))));
    }

    @Override
    public void render(float delta) {
        camera.position.set(playerPositionComponent.x, playerPositionComponent.y, Z);
        gameResources.batch.setProjectionMatrix(camera.combined);
        camera.update();

        renderer.setView(camera);
        renderer.render();
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
