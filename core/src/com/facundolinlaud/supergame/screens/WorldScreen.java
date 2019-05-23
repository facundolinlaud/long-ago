package com.facundolinlaud.supergame.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.factory.*;
import com.facundolinlaud.supergame.listeners.PhysicsEntitiesListener;
import com.facundolinlaud.supergame.managers.world.*;
import com.facundolinlaud.supergame.systems.*;
import com.facundolinlaud.supergame.systems.ai.DecisionMakingSystem;
import com.facundolinlaud.supergame.systems.ai.MoveToSystem;
import com.facundolinlaud.supergame.systems.skills.*;
import com.facundolinlaud.supergame.systems.sprite.AnimableSpriteSystem;
import com.facundolinlaud.supergame.systems.sprite.StackableSpriteSystem;
import com.facundolinlaud.supergame.systems.sprite.StackedSpritesSystem;

/**
 * Created by facundo on 3/18/16.
 */
public class WorldScreen implements Screen {
    private GameResources resources;
    private Factories factories;

    private MapManager mapManager;
    private PhysicsManager physicsManager;
    private UIManager uiManager;
    private ParticleManager particleManager;
    private LightsManager lightsManager;
    private AIManager aiManager;
    private SpawnManager spawnManager;

    private Stage stage;

    public WorldScreen(GameResources resources) {
        this.resources = resources;

        initializeStage();
        initializeFactories();
        initializeManagers();
        initializeListeners();
        initializeEntities();
        initializeSystems();

        System.out.println(ModelFactory.getItemsModel());
    }

    private void initializeStage() {
        this.stage = new Stage(new ScreenViewport());
        this.stage.setDebugAll(false);

        Gdx.input.setInputProcessor(stage);
    }

    private void initializeFactories(){
        this.factories = new Factories();
    }

    private void initializeManagers() {
        this.mapManager = new MapManager(resources.getBatch());
        this.physicsManager = new PhysicsManager(mapManager.getCamera(), mapManager.getMap());
        this.uiManager = new UIManager(stage, mapManager.getCamera());
        this.particleManager = new ParticleManager();
        this.lightsManager = new LightsManager(physicsManager.getWorld(), mapManager.getCamera());
        this.aiManager = new AIManager(factories.getAvailableSkillsFactory(), mapManager, physicsManager);
        this.spawnManager = new SpawnManager(resources.getEngine(), mapManager.getSpawnLocations());
    }

    private void initializeListeners() {
        Engine engine = resources.getEngine();

        engine.addEntityListener(Family.all(BodyComponent.class).get(),
                new PhysicsEntitiesListener(physicsManager.getWorld()));
        engine.addEntityListener(Family.all(AIComponent.class).get(),
                this.aiManager);
    }

    private void initializeEntities(){
        final int X_OFFSET = 24;
        final int Y_OFFSET = 48;

        for(int i = 0; i < 0; i++){
            PlayerFactory.createEnemy(resources.getEngine(), X_OFFSET + i % 2, Y_OFFSET + i % 3);
        }

        PlayerFactory.createPlayer(resources.getEngine());
        lightsManager.setPlayerLightBody(PlayerFactory.getPlayerBody());

        ItemFactory itemFactory = new ItemFactory();

        for(int i = 0; i < 14; i++){
            Entity coin = itemFactory.getItem(13).dropped(21, 48).build();
//            resources.getEngine().addEntity(ItemFactory.createCoins());
            resources.getEngine().addEntity(coin);
        }

        for(int i = 0; i < 4; i++){
            resources.getEngine().addEntity(ItemFactory.createSword());
        }
    }

    private void initializeSystems() {
        PlayerInputObserver playerInputObserver = new PlayerInputObserver();
        stage.addListener(playerInputObserver);
        Engine engine = resources.getEngine();

        engine.addSystem(new ParticleSystem(resources.getBatch()));
        engine.addSystem(new StackableSpriteSystem());
        engine.addSystem(new StackedSpritesSystem());
        engine.addSystem(new AnimableSpriteSystem());
        engine.addSystem(new PlayerInputSystem(playerInputObserver, factories.getAvailableSkillsFactory()));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new CameraFocusSystem(mapManager.getCamera()));
        engine.addSystem(new PhysicsSystem(physicsManager.getWorld()));
        engine.addSystem(new PickUpSystem());
        engine.addSystem(new HealthSystem(resources.getBatch()));
        engine.addSystem(new KeyPressSkillCastingRequestSystem());
        engine.addSystem(new KeyPressThenClickCastingRequestSystem(playerInputObserver));
        engine.addSystem(new SkillCastingSystem(engine, new ParticleFactory(particleManager), lightsManager));
        engine.addSystem(new SkillTargetedSystem());
        engine.addSystem(new SkillLockDownSystem());
        engine.addSystem(new DecisionMakingSystem(this.aiManager));
        engine.addSystem(new MoveToSystem());
        engine.addSystem(new SpawnLocationSystem(resources.getEngine()));

        uiManager.initializeSystems(engine);
    }

    @Override
    public void render(float delta) {
        mapManager.render();

        resources.getBatch().begin();
        resources.getEngine().update(delta);
        resources.getBatch().end();

        mapManager.renderUpperLayer();
        physicsManager.render();
        lightsManager.render();

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
        lightsManager.dispose();
    }

    @Override
    public void show() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}
}
