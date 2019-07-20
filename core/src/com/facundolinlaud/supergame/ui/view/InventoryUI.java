package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.ItemDragTarget;
import com.facundolinlaud.supergame.ui.view.inventory.InventoryWindow;
import com.facundolinlaud.supergame.ui.view.utils.ToggleWindowListener;

import java.util.List;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUI implements UI {
    private static final int ITEM_COUNT = 48;

    private InventoryWindow window;

    public InventoryUI(Stage stage, Skin skin, DragAndDrop dragAndDrop, Table itemDropZone) {
        this.window = new InventoryWindow(skin, ITEM_COUNT, dragAndDrop);

        stage.addActor(window);
        stage.addListener(new ToggleWindowListener(window, Input.Keys.I));
        dragAndDrop.addTarget(new ItemDragTarget(itemDropZone));
    }

    public void updateItems(List<Item> items){
        window.update(items);
    }

    @Override
    public Table get() {
        return this.window;
    }
}