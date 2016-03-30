package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by facundo on 3/29/16.
 */
public class ItemDropZoneUI implements UI {
    private Table table;

    public ItemDropZoneUI(Skin skin) {
        table = new Table(skin);
    }

    @Override
    public Table getUI() {
        return this.table;
    }
}
