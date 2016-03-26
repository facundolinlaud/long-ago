package com.facundolinlaud.supergame.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.facundolinlaud.supergame.systems.ui.InventoryUISystem;
import com.facundolinlaud.supergame.systems.ui.ProfileUISystem;
import com.facundolinlaud.supergame.ui.InventoryUI;
import com.facundolinlaud.supergame.ui.ProfileUI;

/**
 * Created by facundo on 3/25/16.
 */
public class UIManager {
    public static final String DEFAULT_THEME = "default";
    public static final String SKIN_JSON_PATH = "uiskin.json";

    private Skin skin;
    private Stage stage;
    private Table table;

    private ProfileUI profileUI;
    private InventoryUI inventoryUI;

    public UIManager() {
        this.skin = new Skin(Gdx.files.internal(SKIN_JSON_PATH));
        this.stage = new Stage(new ScreenViewport());

        this.table = new Table(skin);
        this.table.setFillParent(true);
        this.table.align(Align.topLeft);
        this.table.setDebug(true);

        this.profileUI = new ProfileUI(table, skin);
        this.inventoryUI = new InventoryUI(table, skin);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    public void initializeSystems(Engine engine){
        engine.addSystem(new ProfileUISystem(profileUI));
        engine.addSystem(new InventoryUISystem(inventoryUI));
    }

    public void render(){
        stage.act();
        stage.draw();
    }
}