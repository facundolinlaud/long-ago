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
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.managers.Renderable;
import com.facundolinlaud.supergame.services.AgentService;
import com.facundolinlaud.supergame.services.EquipmentService;
import com.facundolinlaud.supergame.services.InventoryService;
import com.facundolinlaud.supergame.systems.ui.AttributesUISystem;
import com.facundolinlaud.supergame.systems.ui.ProfileUISystem;
import com.facundolinlaud.supergame.ui.controller.*;
import com.facundolinlaud.supergame.ui.view.*;
import com.facundolinlaud.supergame.ui.view.utils.Window;

import static com.facundolinlaud.supergame.utils.events.Messages.*;

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
    private SkillTreeUI skillTreeUI;
    private DialogUI dialogUI;

    private InventoryUIController inventoryUIController;
    private OverlayUIController overlayUIController;
    private AttributesUIController attributesUIController;
    private EquipmentUIController equipmentUIController;
    private LabelDamagesController labelDamagesController;
    private SkillTreeController skillTreeController;
    private DialogUIController dialogUIController;

    private WindowsOrchestrator windowsOrchestrator;
    private MessageDispatcher messageDispatcher;

    private SkillsFactory skillsFactory;

    public UIManager(Stage stage, Camera camera, AgentService agentService, InventoryService inventoryService,
                     EquipmentService equipmentService, SkillsFactory skillsFactory) {
        this.skin = new Skin(Gdx.files.internal(SKIN_JSON_PATH));
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS_PATH)));
        this.stage = stage;
        this.messageDispatcher = MessageManager.getInstance();
        this.skillsFactory = skillsFactory;

        configureToolTips();
        initializeDragAndDrop();
        initializeViews();
        registerWindows();
        initializeControllers(camera, agentService, inventoryService, equipmentService);
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

    private void initializeDragAndDrop() {
        this.itemsDAD = new DragAndDrop();
        this.itemsDAD.setDragTime(MIN_DRAG_TIME_IN_MILLISECONDS);

        this.skillsDAD = new DragAndDrop();
        this.skillsDAD.setDragTime(MIN_DRAG_TIME_IN_MILLISECONDS);
    }

    private void initializeViews() {
        this.windowsOrchestrator = new WindowsOrchestrator();
        this.overlayUI = new OverlayUI(stage, skin, itemsDAD, skillsDAD, windowsOrchestrator);
        this.inventoryUI = new InventoryUI(skin, itemsDAD);
        this.attributesUI = new AttributesUI(skin);
        this.equipmentUI = new EquipmentUI(skin, itemsDAD);
        this.labelDamagesUI = new LabelDamagesUI(stage, skin);
        this.skillTreeUI = new SkillTreeUI(skin, skillsDAD);
        this.dialogUI = new DialogUI(stage, skin);
    }

    private void initializeControllers(Camera camera, AgentService agentService, InventoryService inventoryService,
                                       EquipmentService equipmentService) {
        this.overlayUIController = new OverlayUIController(overlayUI, agentService);
        this.inventoryUIController = new InventoryUIController(inventoryUI, inventoryService);
        this.attributesUIController = new AttributesUIController(attributesUI);
        this.equipmentUIController = new EquipmentUIController(equipmentUI, inventoryService, equipmentService);
        this.labelDamagesController = new LabelDamagesController(labelDamagesUI, camera);
        this.skillTreeController = new SkillTreeController(skillsFactory, this.skillTreeUI, agentService);
        this.dialogUIController = new DialogUIController(dialogUI);
    }

    private void addUIToStage() {
        this.stage.addActor(this.overlayUI.get());
        this.stage.addActor(this.equipmentUI.get());
        this.stage.addActor(this.inventoryUI.get());
        this.stage.addActor(this.attributesUI.get());
        this.stage.addActor(this.skillTreeUI.get());
    }

    private void subscribeListeners() {
        this.messageDispatcher.addListeners(this.inventoryUIController,
                ITEM_FROM_INVENTORY_DROPPED,
                ITEMS_IN_INVENTORY_SWAPPED,
                INVENTORY_CHANGED);
        this.messageDispatcher.addListeners(this.equipmentUIController,
                ITEM_UNEQUIPPED,
                ITEM_EQUIPPED,
                EQUIPMENT_CHANGED);
        this.messageDispatcher.addListeners(this.overlayUIController,
                REJECTED_SKILL_DUE_TO_NO_MANA,
                REJECTED_SKILL_DUE_TO_NOT_READY,
                REJECTED_SKILL_DUE_TO_WEAPON,
                SKILLS_CHANGED,
                CUSTOM_MESSAGE,
                SKILL_COOLDOWN_START);
        this.messageDispatcher.addListeners(this.skillTreeController,
                SKILL_UNLOCK_REQUEST,
                SKILLS_CHANGED);
        this.messageDispatcher.addListeners(this.attributesUIController, ATTRIBUTE_UPGRADED);
        this.messageDispatcher.addListeners(this.labelDamagesController, ENTITY_ATTACKED);
    }

    private void setCustomCursor() {
        Pixmap pm = new Pixmap(Gdx.files.internal(CUSTOM_CURSOR_PATH));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, CURSOR_X_HOTSPOT, CURSOR_Y_HOTSPOT));
        pm.dispose();
    }

    public void initializeSystems(Engine engine) {
        engine.addSystem(new ProfileUISystem(this.overlayUIController));
        engine.addSystem(new AttributesUISystem(this.attributesUIController));
    }

    private void registerWindows() {
        windowsOrchestrator.register(Window.EQUIPMENT, this.equipmentUI.get());
        windowsOrchestrator.register(Window.INVENTORY, this.inventoryUI.get());
        windowsOrchestrator.register(Window.ATTRIBUTES, this.attributesUI.get());
        windowsOrchestrator.register(Window.SKILL_TREE, this.skillTreeUI.get());
        windowsOrchestrator.register(this.dialogUI);
        stage.addListener(windowsOrchestrator);
    }

    public OverlayUIController getOverlayUIController() {
        return this.overlayUIController;
    }

    public DialogUIController getDialogUIController() {
        return dialogUIController;
    }

    @Override
    public void render() {
        stage.act();
        stage.draw();
    }
}