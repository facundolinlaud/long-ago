package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Created by facundo on 3/27/16.
 */
public class OverlayUI implements UI {

    private Table table;

    private ProfileUI profileUI;
    private ItemDropZoneUI itemDropZoneUI;

    public OverlayUI(Skin skin) {
        this.table = new Table(skin);
        this.table.setFillParent(true);
        this.table.align(Align.topLeft);

        this.profileUI = new ProfileUI(skin);
        this.itemDropZoneUI = new ItemDropZoneUI(skin);

        this.table.add(this.profileUI.getUI()).expandX().fillX().top().left();
        this.table.row();
        this.table.add(this.itemDropZoneUI.getUI()).expand().fill();
    }

    public ProfileUI getProfileUI() {
        return profileUI;
    }

    public Table getItemDropZone(){
        return this.itemDropZoneUI.getUI();
    }

    @Override
    public Table getUI() {
        return this.table;
    }
}
