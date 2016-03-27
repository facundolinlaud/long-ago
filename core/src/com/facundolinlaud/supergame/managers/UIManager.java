package com.facundolinlaud.supergame.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.facundolinlaud.supergame.service.InventoryUIService;
import com.facundolinlaud.supergame.service.impl.InventoryUIServiceImpl;
import com.facundolinlaud.supergame.systems.ui.InventoryUISystem;
import com.facundolinlaud.supergame.systems.ui.ProfileUISystem;
import com.facundolinlaud.supergame.ui.InventoryUI;
import com.facundolinlaud.supergame.ui.OverlayUI;
import com.facundolinlaud.supergame.utils.observer.events.InventoryEvent;

/**
 * Created by facundo on 3/25/16.
 */
public class UIManager {
    public static final String DEFAULT_THEME = "default";
    public static final String SKIN_JSON_PATH = "ui/uiskin.json";
    public static final String TEXTURE_ATLAS_PATH = "ui/uiskin.atlas";

    private Skin skin;
    private Stage stage;

    private OverlayUI hud;
    private InventoryUI inventoryUI;

    private InventoryUIService inventoryUIService;

    public UIManager() {
        this.skin = new Skin(Gdx.files.internal(SKIN_JSON_PATH));
        this.stage = new Stage(new ScreenViewport());
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS_PATH)));

        this.hud = new OverlayUI(skin);
        this.inventoryUI = new InventoryUI(stage, skin);
        this.inventoryUIService = new InventoryUIServiceImpl(inventoryUI);

        addUIToStage();
        addObservers();
    }

    private void addUIToStage() {
        this.stage.addActor(this.hud.getUI());
        Gdx.input.setInputProcessor(stage);
    }

    private void addObservers(){
        this.inventoryUI.addObserver(InventoryEvent.class, this.inventoryUIService);
    }

    public void initializeSystems(Engine engine){
        engine.addSystem(new ProfileUISystem(this.hud.getProfileUI()));
        engine.addSystem(new InventoryUISystem(this.inventoryUIService));
    }

    public void render(){
        stage.act();
        stage.draw();
    }
}