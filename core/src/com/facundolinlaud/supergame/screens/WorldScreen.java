package com.facundolinlaud.supergame.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.facundolinlaud.supergame.factory.AvailableSkillsFactory;
import com.facundolinlaud.supergame.factory.ParticleFactory;
import com.facundolinlaud.supergame.factory.PlayerFactory;
import com.facundolinlaud.supergame.factory.ItemFactory;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.listeners.PhysicsEntitiesListener;
import com.facundolinlaud.supergame.managers.world.*;
import com.facundolinlaud.supergame.systems.*;
import com.facundolinlaud.supergame.systems.skills.*;
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
    private ParticleManager particleManager;

    private Stage stage;

    public WorldScreen(GameResources res) {
        this.res = res;

        initializeStage();
        initializeManagers();
        initializeListeners();
        initializeEntities();
        initializeSystems();
    }

    private void initializeStage() {
        this.stage = new Stage(new ScreenViewport());
        this.stage.setDebugAll(false);
        Gdx.input.setInputProcessor(stage);
    }

    private void initializeManagers() {
        this.mapManager = new MapManager(res.batch);
        this.physicsManager = new PhysicsManager(mapManager.getCamera(), mapManager.getMap());
        this.uiManager = new UIManager(stage);
        this.particleManager = new ParticleManager();
    }

    private void initializeListeners() {
        res.engine.addEntityListener(Family.all(BodyComponent.class).get(),
                new PhysicsEntitiesListener(physicsManager.getWorld()));
    }

    private void initializeEntities(){
        for(int i = 0; i < 4; i++){
            PlayerFactory.createEnemy(res.engine);
        }

        PlayerFactory.createPlayer(res.engine);

        for(int i = 0; i < 14; i++){
            res.engine.addEntity(ItemFactory.createCoins());
        }

        for(int i = 0; i < 4; i++){
            res.engine.addEntity(ItemFactory.createSword());
        }
    }

    private void initializeSystems() {
        PlayerInputObserver playerInputObserver = new PlayerInputObserver();
        stage.addListener(playerInputObserver);
        Engine engine = res.engine;

        engine.addSystem(new ParticleSystem(res.batch));
        engine.addSystem(new StackableSpriteSystem());
        engine.addSystem(new StackedSpritesSystem());
        engine.addSystem(new AnimableSpriteSystem());
        engine.addSystem(new PlayerInputSystem(playerInputObserver, new AvailableSkillsFactory()));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new CameraFocusSystem(mapManager.getCamera()));
        engine.addSystem(new PhysicsSystem(physicsManager.getWorld()));
        engine.addSystem(new PickUpSystem());
        engine.addSystem(new HealthSystem(res.batch));
        engine.addSystem(new KeyPressSkillCastingRequestSystem());
        engine.addSystem(new KeyPressThenClickCastingRequestSystem(playerInputObserver));
        engine.addSystem(new SkillCastingSystem(engine, new ParticleFactory(particleManager)));
        engine.addSystem(new SkillTargetedSystem());
        engine.addSystem(new SkillLockdownSystem());

        uiManager.initializeSystems(engine);
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
