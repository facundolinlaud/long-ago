package com.facundolinlaud.supergame.managers.world;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.managers.Renderable;
import com.facundolinlaud.supergame.systems.ui.*;
import com.facundolinlaud.supergame.ui.controller.*;
import com.facundolinlaud.supergame.ui.view.*;
import com.facundolinlaud.supergame.utils.Messages;

/**
 * Created by facundo on 3/25/16.
 */
public class UIManager implements Renderable {
    private static final String SKIN_JSON_PATH = "ui/redesign/uiskin.json";
    private static final String TEXTURE_ATLAS_PATH = "ui/redesign/uiskin.atlas";
    private static final int MIN_DRAG_TIME_IN_MILLISECONDS = 10;

    private static final String CUSTOM_CURSOR_PATH = "ui/cursor.png";
    private static final int CURSOR_X_HOTSPOT = 9;
    private static final int CURSOR_Y_HOTSPOT = 9;

    private Skin skin;
    private Stage stage;
    private DragAndDrop dragAndDrop;

    private OverlayUI overlayUI;
    private InventoryUI inventoryUI;
    private AttributesUI attributesUI;
    private EquipmentUI equipmentUI;
    private SkillCastingUI skillCastingUI;
    private LabelDamagesUI labelDamagesUI;

    private InventoryUIController inventoryUIController;
    private ProfileUIController profileUIController;
    private AttributesUIController attributesUIController;
    private EquipmentUIController equipmentUIController;
    private SkillCastingUIController skillCastingUIController;
    private LabelDamagesController labelDamagesController;

    private MessageDispatcher messageDispatcher;

    public UIManager(Stage stage, Camera camera) {
        this.skin = new Skin(Gdx.files.internal(SKIN_JSON_PATH));
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS_PATH)));
        this.stage = stage;
        this.messageDispatcher = MessageManager.getInstance();

        initializeUIResources();
        initializeUI();
        initializeServices(camera);
        addUIToStage();
        subscribeReceivers();
        setCustomCursor();
    }

    private void setCustomCursor() {
        Pixmap pm = new Pixmap(Gdx.files.internal(CUSTOM_CURSOR_PATH));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, CURSOR_X_HOTSPOT, CURSOR_Y_HOTSPOT));
        pm.dispose();
    }

    private void initializeUIResources(){
        this.dragAndDrop = new DragAndDrop();
        this.dragAndDrop.setDragTime(MIN_DRAG_TIME_IN_MILLISECONDS);
    }

    private void initializeUI() {
        this.overlayUI = new OverlayUI(skin);
        this.inventoryUI = new InventoryUI(stage, skin, dragAndDrop, overlayUI.getItemDropZone());
        this.attributesUI = new AttributesUI(stage, skin);
        this.equipmentUI = new EquipmentUI(stage, skin, dragAndDrop, overlayUI.getItemDropZone());
        this.skillCastingUI = new SkillCastingUI();
        this.labelDamagesUI = new LabelDamagesUI(stage, skin);
    }

    private void initializeServices(Camera camera) {
        this.profileUIController = new ProfileUIController(this.overlayUI);
        this.inventoryUIController = new InventoryUIController(this.inventoryUI);
        this.attributesUIController = new AttributesUIController(this.attributesUI);
        this.equipmentUIController = new EquipmentUIController(this.equipmentUI);
        this.skillCastingUIController = new SkillCastingUIController(this.skillCastingUI, this.overlayUI);
        this.labelDamagesController = new LabelDamagesController(this.labelDamagesUI, camera);
    }

    private void addUIToStage() {
        this.stage.addActor(this.overlayUI.get());
        this.stage.addActor(this.skillCastingUI);
    }

    private void subscribeReceivers(){
        this.messageDispatcher.addListener(this.inventoryUIController, Messages.ITEM_FROM_INVENTORY_DROPPED);
        this.messageDispatcher.addListener(this.inventoryUIController, Messages.ITEMS_IN_INVENTORY_SWAPPED);
        this.messageDispatcher.addListener(this.attributesUIController, Messages.ATTRIBUTE_UPGRADED);
        this.messageDispatcher.addListener(this.equipmentUIController, Messages.ITEM_UNEQUIPPED);
        this.messageDispatcher.addListener(this.equipmentUIController, Messages.ITEM_EQUIPPED);
        this.messageDispatcher.addListener(this.overlayUI, Messages.SKILL_CASTED);
        this.messageDispatcher.addListener(this.labelDamagesController, Messages.ENTITY_ATTACKED);
    }

    public void initializeSystems(Engine engine){
        engine.addSystem(new ProfileUISystem(this.overlayUI));
        engine.addSystem(new InventoryUISystem(this.inventoryUIController));
        engine.addSystem(new AttributesUISystem(this.attributesUIController));
        engine.addSystem(new EquipmentUISystem(this.equipmentUIController));
        engine.addSystem(new SkillCastingUISystem(this.skillCastingUIController));
    }

    @Override
    public void render(){
        stage.act();
        stage.draw();
    }
}