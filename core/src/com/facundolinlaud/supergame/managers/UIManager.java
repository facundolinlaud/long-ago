package com.facundolinlaud.supergame.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.facundolinlaud.supergame.service.InventoryUIService;
import com.facundolinlaud.supergame.service.impl.InventoryUIServiceImpl;
import com.facundolinlaud.supergame.systems.ui.InventoryUISystem;
import com.facundolinlaud.supergame.systems.ui.ProfileUISystem;
import com.facundolinlaud.supergame.ui.InventoryUI;
import com.facundolinlaud.supergame.ui.ProfileUI;
import com.facundolinlaud.supergame.ui.RootUI;
import com.facundolinlaud.supergame.utils.observer.events.InventoryEvent;
import com.sun.corba.se.spi.orbutil.fsm.Input;

/**
 * Created by facundo on 3/25/16.
 */
public class UIManager {
    public static final String DEFAULT_THEME = "default";
    public static final String SKIN_JSON_PATH = "ui/uiskin.json";
    public static final String TEXTURE_ATLAS_PATH = "ui/uiskin.atlas";

    private Skin skin;
    private Stage stage;

    private RootUI rootUI;

    private InventoryUIService inventoryUIService;

    public UIManager() {
        this.skin = new Skin(Gdx.files.internal(SKIN_JSON_PATH));
        this.stage = new Stage(new ScreenViewport());
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS_PATH)));

        this.rootUI = new RootUI(skin);
        this.inventoryUIService = new InventoryUIServiceImpl(this.rootUI.getInventoryUI());

        this.stage.addActor(rootUI.getUI());
        Gdx.input.setInputProcessor(stage);

        addObservers();
    }

    private void addObservers(){
        this.rootUI.getInventoryUI().addObserver(InventoryEvent.class, this.inventoryUIService);
    }

    public void initializeSystems(Engine engine){
        engine.addSystem(new ProfileUISystem(this.rootUI.getProfileUI()));
        engine.addSystem(new InventoryUISystem(this.inventoryUIService));
    }

    public void render(){
        stage.act();
        stage.draw();
    }
}