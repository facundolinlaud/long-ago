package com.facundolinlaud.supergame.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.facundolinlaud.supergame.components.BodyComponent;
import com.facundolinlaud.supergame.components.ai.AIComponent;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.listeners.PhysicsEntitiesListener;
import com.facundolinlaud.supergame.listeners.ProjectilesCollisionListener;
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
    private MessageDispatcher messageDispatcher;

    private CameraManager cameraManager;
    private MapManager mapManager;
    private PhysicsManager physicsManager;
    private AIManager aiManager;
    private SpawnManager spawnManager;
    private WorldEntitiesManager weManager;
    private UIManager uiManager;
    private LightsManager lightsManager;
    private PlayerInputManager playerInputManager;
    private QuestsManager questsManager;

    private Stage stage;

    public WorldScreen(GameResources resources) {
        this.messageDispatcher = MessageManager.getInstance();
        this.resources = resources;

        initializeStage();
        initializeFactories();
        initializeManagers();
        initializeListeners();
        initializeSystems();
    }

    private void initializeStage() {
        this.stage = new Stage(new ScreenViewport());
        this.stage.setDebugAll(false);

        Gdx.input.setInputProcessor(stage);
    }

    private void initializeFactories(){
        this.factories = new Factories(resources.getEngine());
    }

    private void initializeManagers() {
        this.cameraManager = new CameraManager();
        this.mapManager = new MapManager(resources.getBatch(), cameraManager.getCamera());
        this.physicsManager = new PhysicsManager(mapManager.getCamera(), mapManager.getMap());
        this.aiManager = new AIManager(mapManager, physicsManager);
        this.spawnManager = new SpawnManager(resources.getEngine(), mapManager.getSpawnLocations());
        this.weManager = new WorldEntitiesManager(resources.getEngine(), factories);
        this.uiManager = new UIManager(stage, mapManager.getCamera(), weManager.getPlayer());
        this.lightsManager = new LightsManager(physicsManager.getWorld(), mapManager.getCamera(), weManager.getPlayer());
        this.playerInputManager = new PlayerInputManager();
        this.questsManager = new QuestsManager(weManager.getPlayer(), uiManager.getDialogUIController(),
                factories.getAgentFactory(), resources.getEngine());
    }

    private void initializeListeners() {
        Engine engine = resources.getEngine();

        engine.addEntityListener(Family.all(BodyComponent.class).get(),
                new PhysicsEntitiesListener(physicsManager.getWorld()));
        engine.addEntityListener(Family.all(AIComponent.class).get(),
                this.aiManager);

        this.physicsManager.getWorld().setContactListener(new ProjectilesCollisionListener(engine,
                factories.getParticleFactory(), lightsManager, cameraManager));

        this.stage.addListener(playerInputManager);
    }

    private void initializeSystems() {
        Engine engine = resources.getEngine();

        engine.addSystem(new ParticleSystem(resources.getBatch()));
        engine.addSystem(new StackableSpriteSystem());
        engine.addSystem(new StackedSpritesSystem());
        engine.addSystem(new AnimableSpriteSystem());
        engine.addSystem(new PlayerInputSystem(playerInputManager, factories.getSkillsFactory(),
                uiManager.getOverlayUIController()));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new CameraFocusSystem(cameraManager));
        engine.addSystem(new PhysicsSystem(physicsManager.getWorld()));
        engine.addSystem(new PickUpSystem());
        engine.addSystem(new KeyPressSkillCastingRequestSystem());
        engine.addSystem(new KeyPressThenClickCastingRequestSystem(playerInputManager));
        engine.addSystem(new SkillCastingSystem(engine, factories.getParticleFactory(),
                lightsManager, cameraManager));
        engine.addSystem(new SkillTargetedSystem());
        engine.addSystem(new SkillLockDownSystem());
        engine.addSystem(new DecisionMakingSystem(aiManager));
        engine.addSystem(new MoveToSystem());
        engine.addSystem(new SpawnLocationSystem(factories.getAgentFactory()));
        engine.addSystem(new ProjectileSystem(engine));
        engine.addSystem(new SkillCoolDownSystem());
        engine.addSystem(new HealthSystem(resources.getBatch()));
        engine.addSystem(new InteractionSystem(uiManager.getDialogUIController(), playerInputManager, weManager));

        uiManager.initializeSystems(engine);
    }

    @Override
    public void render(float delta) {
        messageDispatcher.update();
        cameraManager.render(delta);
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
        cameraManager.resize(width, height);
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
