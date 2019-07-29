package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.inventory.InventoryWindow;

import java.util.List;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUI implements UI {
    private static final int ITEM_COUNT = 48;

    private InventoryWindow window;

    public InventoryUI(Skin skin, DragAndDrop dragAndDrop) {
        this.window = new InventoryWindow(skin, ITEM_COUNT, dragAndDrop);
        this.window.setPosition(0, 328);
    }

    public void updateItems(List<Item> items){
        window.update(items);
    }

    @Override
    public Table get() {
        return this.window;
    }
}