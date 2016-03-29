package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.facundolinlaud.supergame.managers.Manager;
import com.facundolinlaud.supergame.service.InventoryUIService;
import com.facundolinlaud.supergame.service.ProfileUIService;
import com.facundolinlaud.supergame.service.impl.InventoryUIServiceImpl;
import com.facundolinlaud.supergame.service.impl.ProfileUIServiceImpl;
import com.facundolinlaud.supergame.systems.ui.InventoryUISystem;
import com.facundolinlaud.supergame.systems.ui.ProfileUISystem;
import com.facundolinlaud.supergame.ui.InventoryUI;
import com.facundolinlaud.supergame.ui.OverlayUI;
import com.facundolinlaud.supergame.utils.mediator.Mediator;
import com.facundolinlaud.supergame.utils.mediator.events.ItemDroppedEvent;

/**
 * Created by facundo on 3/25/16.
 */
public class UIManager implements Manager {
    public static final String DEFAULT_THEME = "default";
    public static final String SKIN_JSON_PATH = "ui/first_iteration/uiskin.json";
    public static final String TEXTURE_ATLAS_PATH = "ui/first_iteration/uiskin.atlas";

    private Skin skin;
    private Stage stage;
    private Mediator uiMediator;

    private OverlayUI hud;
    private InventoryUI inventoryUI;

    private InventoryUIService inventoryUIService;
    private ProfileUIService profileUIService;

    public UIManager() {
        this.skin = new Skin(Gdx.files.internal(SKIN_JSON_PATH));
        this.stage = new Stage(new ScreenViewport());
        this.stage.setDebugAll(true);
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS_PATH)));
        this.uiMediator = new Mediator();

        initializeUI();
        initializeServices();
        addUIToStage();
        addObservers();
    }

    private void initializeUI() {
        this.hud = new OverlayUI(skin);
        this.inventoryUI = new InventoryUI(uiMediator, stage, skin, this.hud.getItemDropZone());
    }

    private void initializeServices() {
        this.inventoryUIService = new InventoryUIServiceImpl(inventoryUI);
        this.profileUIService = new ProfileUIServiceImpl(this.hud.getProfileUI());
    }

    private void addUIToStage() {
        this.stage.addActor(this.hud.getUI());
        Gdx.input.setInputProcessor(stage);
    }

    private void addObservers(){
        this.uiMediator.subscribe(ItemDroppedEvent.class, this.inventoryUIService);
    }

    public void initializeSystems(Engine engine){
        engine.addSystem(new ProfileUISystem(this.hud.getProfileUI()));
        engine.addSystem(new InventoryUISystem(this.inventoryUIService));
    }

    @Override
    public void render(){
        stage.act();
        stage.draw();
    }
}