package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.managers.Renderable;
import com.facundolinlaud.supergame.systems.ui.AttributesUISystem;
import com.facundolinlaud.supergame.systems.ui.ProfileUISystem;
import com.facundolinlaud.supergame.systems.ui.SkillCastingUISystem;
import com.facundolinlaud.supergame.ui.controller.*;
import com.facundolinlaud.supergame.ui.view.*;
import com.facundolinlaud.supergame.utils.events.Messages;

/**
 * Created by facundo on 3/25/16.
 */
public class UIManager implements Renderable {
    private static final String SKIN_JSON_PATH = "ui/skin/uiskin.json";
    private static final String TEXTURE_ATLAS_PATH = "ui/skin/uiskin.atlas";
    private static final int MIN_DRAG_TIME_IN_MILLISECONDS = 7;

    private static final String CUSTOM_CURSOR_PATH = "ui/cursor.png";
    private static final int CURSOR_X_HOTSPOT = 9;
    private static final int CURSOR_Y_HOTSPOT = 9;

    private Skin skin;
    private Stage stage;
    private DragAndDrop itemsDAD;
    private DragAndDrop skillsDAD;

    private OverlayUI overlayUI;
    private InventoryUI inventoryUI;
    private AttributesUI attributesUI;
    private EquipmentUI equipmentUI;
    private LabelDamagesUI labelDamagesUI;
    private SkillsUI skillsUI;

    private InventoryUIController inventoryUIController;
    private OverlayUIController overlayUIController;
    private AttributesUIController attributesUIController;
    private EquipmentUIController equipmentUIController;
    private LabelDamagesController labelDamagesController;

    private MessageDispatcher messageDispatcher;

    public UIManager(Stage stage, Camera camera, Entity player, SkillsFactory skillsFactory) {
        this.skin = new Skin(Gdx.files.internal(SKIN_JSON_PATH));
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS_PATH)));
        this.stage = stage;
        this.messageDispatcher = MessageManager.getInstance();

        configureToolTips();
        initializeDragAndDrop();
        initializeViews(skillsFactory);
        initializeControllers(camera, player);
        addUIToStage();
        subscribeListeners();
        setCustomCursor();
    }

    private void configureToolTips() {
        TooltipManager tm = TooltipManager.getInstance();
        tm.animations = false;
        tm.initialTime = 0.1f;
        tm.offsetX = 2;
        tm.offsetY = 2;
        tm.instant();
    }

    private void initializeDragAndDrop(){
        this.itemsDAD = new DragAndDrop();
        this.itemsDAD.setDragTime(MIN_DRAG_TIME_IN_MILLISECONDS);

        this.skillsDAD = new DragAndDrop();
        this.skillsDAD.setDragTime(MIN_DRAG_TIME_IN_MILLISECONDS);
    }

    private void initializeViews(SkillsFactory skillsFactory) {
        this.overlayUI = new OverlayUI(skin, itemsDAD, skillsDAD);
        this.inventoryUI = new InventoryUI(stage, skin, itemsDAD);
        this.attributesUI = new AttributesUI(stage, skin);
        this.equipmentUI = new EquipmentUI(stage, skin, itemsDAD);
        this.labelDamagesUI = new LabelDamagesUI(stage, skin);
        this.skillsUI = new SkillsUI(stage, skin, skillsDAD, skillsFactory);
    }

    private void initializeControllers(Camera camera, Entity player) {
        this.overlayUIController = new OverlayUIController(this.overlayUI);
        this.inventoryUIController = new InventoryUIController(this.inventoryUI, player);
        this.attributesUIController = new AttributesUIController(this.attributesUI);
        this.equipmentUIController = new EquipmentUIController(this.equipmentUI, player);
        this.labelDamagesController = new LabelDamagesController(this.labelDamagesUI, camera);
    }

    private void addUIToStage() {
        this.stage.addActor(this.overlayUI.get());
    }

    private void subscribeListeners(){
        this.messageDispatcher.addListener(this.inventoryUIController, Messages.ITEM_FROM_INVENTORY_DROPPED);
        this.messageDispatcher.addListener(this.inventoryUIController, Messages.ITEMS_IN_INVENTORY_SWAPPED);
        this.messageDispatcher.addListener(this.inventoryUIController, Messages.INVENTORY_CHANGED);
        this.messageDispatcher.addListener(this.attributesUIController, Messages.ATTRIBUTE_UPGRADED);
        this.messageDispatcher.addListener(this.equipmentUIController, Messages.ITEM_UNEQUIPPED);
        this.messageDispatcher.addListener(this.equipmentUIController, Messages.ITEM_EQUIPPED);
        this.messageDispatcher.addListener(this.equipmentUIController, Messages.EQUIPMENT_CHANGED);
        this.messageDispatcher.addListener(this.overlayUIController, Messages.SKILL_CASTED);
        this.messageDispatcher.addListener(this.overlayUIController, Messages.REJECTED_SKILL_DUE_TO_NO_MANA);
        this.messageDispatcher.addListener(this.overlayUIController, Messages.REJECTED_SKILL_DUE_TO_NOT_READY);
        this.messageDispatcher.addListener(this.labelDamagesController, Messages.ENTITY_ATTACKED);
    }

    private void setCustomCursor() {
        Pixmap pm = new Pixmap(Gdx.files.internal(CUSTOM_CURSOR_PATH));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, CURSOR_X_HOTSPOT, CURSOR_Y_HOTSPOT));
        pm.dispose();
    }

    public void initializeSystems(Engine engine){
        engine.addSystem(new ProfileUISystem(this.overlayUIController));
        engine.addSystem(new AttributesUISystem(this.attributesUIController));
        engine.addSystem(new SkillCastingUISystem(this.overlayUIController));
    }

    public OverlayUIController getOverlayUIController(){
        return this.overlayUIController;
    }

    @Override
    public void render(){
        stage.act();
        stage.draw();
    }
}