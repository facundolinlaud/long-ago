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
import com.facundolinlaud.supergame.components.ai.BehaviorComponent;
import com.facundolinlaud.supergame.engine.GameResources;
import com.facundolinlaud.supergame.factory.Factories;
import com.facundolinlaud.supergame.factory.PhysicsFactory;
import com.facundolinlaud.supergame.listeners.PhysicsEntitiesListener;
import com.facundolinlaud.supergame.listeners.ProjectilesCollisionListener;
import com.facundolinlaud.supergame.managers.world.*;
import com.facundolinlaud.supergame.services.*;
import com.facundolinlaud.supergame.systems.*;
import com.facundolinlaud.supergame.systems.ai.BehaviorSystem;
import com.facundolinlaud.supergame.systems.ai.PursueSystem;
import com.facundolinlaud.supergame.systems.ai.TraverseSystem;
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

    private AgentService agentService;
    private CombatService combatService;
    private ParticlesService particlesService;
    private ProjectilesService projectilesService;
    private InventoryService inventoryService;
    private EquipmentService equipmentService;

    private CameraManager cameraManager;
    private MapManager mapManager;
    private PhysicsManager physicsManager;
    private BehaviorManager behaviorManager;
    private SpawnManager spawnManager;
    private UIManager uiManager;
    private LightsManager lightsManager;
    private PlayerInputManager playerInputManager;
    private QuestsManager questsManager;
    private PathFindingManager pathFindingManager;
    private SkillsManager skillsManager;

    private Stage stage;

    public WorldScreen(GameResources resources) {
        this.messageDispatcher = MessageManager.getInstance();
        this.resources = resources;

        initializeStage();
        initializeFactories();
        initializeServices();
        initializeManagers();
        initializeListeners();
        initializeSystems();
    }

    private void initializeStage() {
        this.stage = new Stage(new ScreenViewport());
        this.stage.setDebugAll(false);

        Gdx.input.setInputProcessor(stage);
    }

    private void initializeFactories() {
        this.factories = new Factories(resources.getEngine());
    }

    private void initializeServices() {
        this.agentService = new AgentService(resources.getEngine(), factories.getAgentFactory());
        this.combatService = new CombatService(resources.getEngine());
        this.particlesService = new ParticlesService(resources.getEngine(), factories.getParticleFactory());
        this.projectilesService = new ProjectilesService(resources.getEngine(), factories.getParticleFactory());
        this.inventoryService = new InventoryService(resources.getEngine(), agentService);
        this.equipmentService = new EquipmentService(resources.getEngine(), agentService);
    }

    private void initializeManagers() {
        this.cameraManager = new CameraManager();
        this.mapManager = new MapManager(resources.getBatch(), cameraManager.getCamera());
        this.physicsManager = new PhysicsManager(mapManager.getCamera(), mapManager.getMap());
        this.spawnManager = new SpawnManager(resources.getEngine(), mapManager.getSpawnLocations());
        this.uiManager = new UIManager(stage, mapManager.getCamera(), agentService, inventoryService, equipmentService,
                factories.getSkillsFactory());
        this.lightsManager = new LightsManager(PhysicsFactory.get().getWorld(), mapManager.getCamera(), agentService);
        this.playerInputManager = new PlayerInputManager(cameraManager);
        this.skillsManager = new SkillsManager(factories.getSkillsFactory(), lightsManager, cameraManager, uiManager,
                agentService, combatService, particlesService, projectilesService);
        this.questsManager = new QuestsManager(factories, lightsManager, cameraManager, skillsManager, uiManager,
                agentService, combatService, particlesService, projectilesService);
        this.pathFindingManager = new PathFindingManager(mapManager, physicsManager);
        this.behaviorManager = new BehaviorManager(skillsManager, lightsManager, cameraManager, uiManager, agentService,
                combatService, particlesService, projectilesService, pathFindingManager);
    }

    private void initializeListeners() {
        Engine engine = resources.getEngine();

        engine.addEntityListener(Family.all(BehaviorComponent.class).get(),
                this.behaviorManager);

        engine.addEntityListener(Family.all(BodyComponent.class).get(),
                new PhysicsEntitiesListener(PhysicsFactory.get().getWorld()));

        PhysicsFactory.get().getWorld().setContactListener(new ProjectilesCollisionListener(projectilesService));

        this.stage.addListener(playerInputManager);
    }

    OverlayRenderSystem overlayRenderSystem;

    private void initializeSystems() {
        Engine engine = resources.getEngine();

        engine.addSystem(new ParticleSystem(resources.getBatch()));
        engine.addSystem(new StackableSpriteSystem());
        engine.addSystem(new StackedSpritesSystem());
        engine.addSystem(new AnimableSpriteSystem());
        engine.addSystem(new PlayerInputSystem(playerInputManager, skillsManager, cameraManager,
                factories.getSkillsFactory(), uiManager.getOverlayUIController()));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new CameraFocusSystem(cameraManager));
        engine.addSystem(new PhysicsSystem(PhysicsFactory.get().getWorld()));
        engine.addSystem(new PickUpSystem());
        engine.addSystem(new BehaviorSystem(behaviorManager, agentService));
        engine.addSystem(new PursueSystem(pathFindingManager));
        engine.addSystem(new TraverseSystem());
        engine.addSystem(new SpawnLocationSystem(factories.getAgentFactory()));
        engine.addSystem(new ProjectileSystem(engine));
        engine.addSystem(new HealthSystem(resources.getBatch()));
        engine.addSystem(new InteractionSystem(uiManager.getDialogUIController(), playerInputManager, agentService));
        engine.addSystem(new SkillCoolDownSystem());
        engine.addSystem(new OverlayRenderSystem(cameraManager, resources));

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
        questsManager.tick(delta);
        skillsManager.tick(delta);
        behaviorManager.tick(delta);

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
    public void show() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
