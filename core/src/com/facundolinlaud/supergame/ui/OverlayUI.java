package com.facundolinlaud.supergame.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

/**
 * Created by facundo on 3/27/16.
 */
public class OverlayUI implements UI {

    private Table table;

    private ProfileUI profileUI;

    public OverlayUI(Skin skin) {
        this.table = new Table(skin);
        this.table.setFillParent(true);
        this.table.align(Align.topLeft);
        this.table.setDebug(true);

        this.profileUI = new ProfileUI(skin);

        this.table.add(this.profileUI.getUI()).expandX().fillX().top().left();
    }

    public ProfileUI getProfileUI() {
        return profileUI;
    }

    @Override
    public Table getUI() {
        return this.table;
    }
}
