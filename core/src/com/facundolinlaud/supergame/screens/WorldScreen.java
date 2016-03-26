package com.facundolinlaud.supergame.screens;

import com.badlogic.gdx.Screen;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.factory.BodyFactory;
import com.facundolinlaud.supergame.factory.EntityFactory;
import com.facundolinlaud.supergame.managers.MapManager;
import com.facundolinlaud.supergame.managers.PhysicsManager;
import com.facundolinlaud.supergame.managers.UIManager;
import com.facundolinlaud.supergame.systems.*;

// * ver si la animacion la puedo encarar por el lado de getLinearVelocity

/**
 * Created by facundo on 3/18/16.
 */
public class WorldScreen implements Screen {
    private GameResources res;

    private MapManager mapManager;
    private PhysicsManager physicsManager;
    private BodyFactory bodyFactory;
    private EntityFactory entityFactory;
    private UIManager uiManager;

    public WorldScreen(GameResources res) {
        this.res = res;

        mapManager = new MapManager(res.batch);
        physicsManager = new PhysicsManager(mapManager.getCamera(), mapManager.getMap());
        uiManager = new UIManager();
        bodyFactory = new BodyFactory(physicsManager.getWorld());
        entityFactory = new EntityFactory();

        initializeEntities();
        initializeSystems();
    }

    private void initializeEntities(){
        res.engine.addEntity(entityFactory.createPlayerWithBody(bodyFactory.createPlayer()));
    }

    private void initializeSystems() {
        res.engine.addSystem(new AnimationSystem());
        res.engine.addSystem(new KeyboardSystem());
        res.engine.addSystem(new MovementSystem());
        res.engine.addSystem(new CameraFocusSystem(mapManager.getCamera()));
        res.engine.addSystem(new PhysicsSystem(physicsManager.getWorld()));

        uiManager.initializeSystems(res.engine);
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
