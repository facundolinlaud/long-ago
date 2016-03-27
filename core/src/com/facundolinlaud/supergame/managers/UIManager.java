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

/**
 * Created by facundo on 3/25/16.
 */
public class UIManager {
    public static final String DEFAULT_THEME = "default";
    public static final String SKIN_JSON_PATH = "ui/uiskin.json";
    public static final String TEXTURE_ATLAS_PATH = "ui/uiskin.atlas";

    private Skin skin;
    private Stage stage;
    private Table table;

    private ProfileUI profileUI;
    private InventoryUI inventoryUI;

    private InventoryUIService inventoryUIService;

    public UIManager() {
        this.skin = new Skin(Gdx.files.internal(SKIN_JSON_PATH));
        this.stage = new Stage(new ScreenViewport());
        this.skin.addRegions(new TextureAtlas(Gdx.files.internal(TEXTURE_ATLAS_PATH)));

        this.table = new Table(skin);
        this.table.setFillParent(true);
        this.table.align(Align.topLeft);
        this.table.setDebug(true);

        this.profileUI = new ProfileUI(skin);
        this.inventoryUI = new InventoryUI(skin);

        this.inventoryUIService = new InventoryUIServiceImpl(this.inventoryUI);
        this.inventoryUI.setUiService(this.inventoryUIService);

        this.table.add(this.profileUI.getUI()).expandX().fillX().top().left();
        this.table.add(this.inventoryUI.getUI()).fillY().width(300).prefHeight(150);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    public void initializeSystems(Engine engine){
        engine.addSystem(new ProfileUISystem(this.profileUI));
        engine.addSystem(new InventoryUISystem(this.inventoryUIService));
    }

    public void render(){
        stage.act();
        stage.draw();
    }
}