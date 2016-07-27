package com.facundolinlaud.supergame.screens;

import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Screen;
import com.facundolinlaud.supergame.factory.PlayerFactory;
import com.facundolinlaud.supergame.factory.ItemFactory;
import com.facundolinlaud.supergame.factory.PhysicsFactory;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.listeners.PhysicsEntitiesListener;
import com.facundolinlaud.supergame.managers.world.MapManager;
import com.facundolinlaud.supergame.managers.world.PhysicsManager;
import com.facundolinlaud.supergame.managers.world.UIManager;
import com.facundolinlaud.supergame.systems.*;
import com.facundolinlaud.supergame.systems.sprite.AnimableSpriteSystem;
import com.facundolinlaud.supergame.systems.sprite.StackableSpriteSystem;
import com.facundolinlaud.supergame.systems.sprite.StackedSpritesSystem;

/**
 * Created by facundo on 3/18/16.
 */
public class WorldScreen implements Screen {
    private GameResources res;

    private MapManager mapManager;
    private PhysicsManager physicsManager;
    private UIManager uiManager;

    private PhysicsFactory physicsFactory;

    public WorldScreen(GameResources res) {
        this.res = res;

        mapManager = new MapManager(res.batch);
        physicsManager = new PhysicsManager(mapManager.getCamera(), mapManager.getMap());
        uiManager = new UIManager();
        physicsFactory = PhysicsFactory.get();

        initializeListeners();
        initializeEntities();
        initializeSystems();
    }

    private void initializeListeners() {
        res.engine.addEntityListener(Family.all(BodyComponent.class).get(), new PhysicsEntitiesListener(physicsManager.getWorld()));
    }

    private void initializeEntities(){
        res.engine.addEntity(PlayerFactory.getPlayerEntity());

        for(float i = 0; i < 8; i++){
            res.engine.addEntity(ItemFactory.createCoins());
        }

        for(float i = 0; i < 4; i++){
            res.engine.addEntity(ItemFactory.createSword());
        }
    }

    private void initializeSystems() {
        res.engine.addSystem(new StackableSpriteSystem());
        res.engine.addSystem(new StackedSpritesSystem());
        res.engine.addSystem(new AnimableSpriteSystem());
        res.engine.addSystem(new KeyboardSystem());
        res.engine.addSystem(new MovementSystem());
        res.engine.addSystem(new CameraFocusSystem(mapManager.getCamera()));
        res.engine.addSystem(new PhysicsSystem(physicsManager.getWorld()));
        res.engine.addSystem(new PickUpSystem());

        uiManager.initializeSystems(res.engine);
    }

    @Override
    public void render(float delta) {
        mapManager.render();

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
