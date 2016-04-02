package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.facundolinlaud.supergame.managers.Manager;
import com.facundolinlaud.supergame.systems.ui.AttributesUISystem;
import com.facundolinlaud.supergame.systems.ui.EquipmentUISystem;
import com.facundolinlaud.supergame.ui.controller.AttributesUIController;
import com.facundolinlaud.supergame.ui.controller.EquipmentUIController;
import com.facundolinlaud.supergame.ui.controller.InventoryUIController;
import com.facundolinlaud.supergame.ui.controller.ProfileUIController;
import com.facundolinlaud.supergame.ui.controller.impl.AttributesUIControllerImpl;
import com.facundolinlaud.supergame.ui.controller.impl.EquipmentUIControllerImpl;
import com.facundolinlaud.supergame.ui.controller.impl.InventoryUIControllerImpl;
import com.facundolinlaud.supergame.ui.controller.impl.ProfileUIControllerImpl;
import com.facundolinlaud.supergame.systems.ui.InventoryUISystem;
import com.facundolinlaud.supergame.systems.ui.ProfileUISystem;
import com.facundolinlaud.supergame.ui.view.AttributesUI;
import com.facundolinlaud.supergame.ui.view.EquipmentUI;
import com.facundolinlaud.supergame.ui.view.InventoryUI;
import com.facundolinlaud.supergame.ui.view.OverlayUI;
import com.facundolinlaud.supergame.utils.events.AttributeUpgradeEvent;
import com.facundolinlaud.supergame.utils.events.ItemsPositionSwapEvent;
import com.facundolinlaud.supergame.utils.mediator.Mediator;
import com.facundolinlaud.supergame.utils.events.ItemDroppedEvent;

/**
 * Created by facundo on 3/25/16.
 */
public class UIManager implements Manager {
    public static final String SKIN_JSON_PATH = "ui/second_iteration/uiskin.json";
    public static final String TEXTURE_ATLAS_PATH = "ui/second_iteration/uiskin.atlas";

    private Skin skin;
    private Stage stage;
    private Mediator uiMediator;

    private OverlayUI hud;
    private InventoryUI inventoryUI;
    private AttributesUI attributesUI;
    private EquipmentUI equipmentUI;

    private InventoryUIController inventoryUIController;
    private ProfileUIController profileUIController;
    private AttributesUIController attributesUIController;
    private EquipmentUIController equipmentUIController;

    public UIManager() {
        this.skin = new Skin(Gdx.files.internal(SKIN_JSON_PATH));
        this.stage = new Stage(new ScreenViewport());
        this.stage.setDebugAll(true);
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS_PATH)));
        this.uiMediator = new Mediator();

        initializeUI();
        initializeServices();
        addUIToStage();
        subscribeReceivers();
    }

    private void initializeUI() {
        this.hud = new OverlayUI(skin);
        this.inventoryUI = new InventoryUI(uiMediator, stage, skin, this.hud.getItemDropZone());
        this.attributesUI = new AttributesUI(uiMediator, stage, skin);
        this.equipmentUI = new EquipmentUI(uiMediator, stage, skin);
    }

    private void initializeServices() {
        this.profileUIController = new ProfileUIControllerImpl(this.hud);
        this.inventoryUIController = new InventoryUIControllerImpl(this.inventoryUI);
        this.attributesUIController = new AttributesUIControllerImpl(this.attributesUI);
        this.equipmentUIController = new EquipmentUIControllerImpl(this.equipmentUI);
    }

    private void addUIToStage() {
        this.stage.addActor(this.hud.get());
        Gdx.input.setInputProcessor(stage);
    }

    private void subscribeReceivers(){
        this.uiMediator.subscribe(ItemDroppedEvent.class, this.inventoryUIController);
        this.uiMediator.subscribe(ItemsPositionSwapEvent.class, this.inventoryUIController);
        this.uiMediator.subscribe(AttributeUpgradeEvent.class, this.attributesUIController);
    }

    public void initializeSystems(Engine engine){
        engine.addSystem(new ProfileUISystem(this.hud));
        engine.addSystem(new InventoryUISystem(this.inventoryUIController));
        engine.addSystem(new AttributesUISystem(this.attributesUIController));
        engine.addSystem(new EquipmentUISystem(this.equipmentUIController));
    }

    @Override
    public void render(){
        stage.act();
        stage.draw();
    }
}