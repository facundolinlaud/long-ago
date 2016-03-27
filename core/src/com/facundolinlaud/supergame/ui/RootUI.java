package com.facundolinlaud.supergame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Created by facundo on 3/27/16.
 */
public class RootUI implements UI {

    private Table table;

    private ProfileUI profileUI;
    private InventoryUI inventoryUI;

    public RootUI(Skin skin) {
        this.table = new Table(skin);
        this.table.setFillParent(true);
        this.table.align(Align.topLeft);
        this.table.setDebug(true);

        this.profileUI = new ProfileUI(skin);
        this.inventoryUI = new InventoryUI(skin);

        this.table.add(this.profileUI.getUI()).expandX().fillX().top().left();
        this.table.add(this.inventoryUI.getUI()).fillY().width(300).prefHeight(150);
    }

    @Override
    public Table getUI() {
        return this.table;
    }

    public ProfileUI getProfileUI() {
        return profileUI;
    }

    public InventoryUI getInventoryUI() {
        return inventoryUI;
    }
}
