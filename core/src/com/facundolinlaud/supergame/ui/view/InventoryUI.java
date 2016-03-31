package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.inventory.DropAreaTarget;
import com.facundolinlaud.supergame.ui.view.utils.ToggleWindowListener;
import com.facundolinlaud.supergame.ui.view.inventory.InventoryWindow;
import com.facundolinlaud.supergame.utils.mediator.Mediator;

import java.util.List;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUI implements UI {
    private static final int MIN_DRAG_TIME_IN_MILLISECONDS = 10;
    private static final int ITEM_COUNT = 17;

    private InventoryWindow window;

    public InventoryUI(Mediator uiMediator, Stage stage, Skin skin, Table itemDropZone) {
        this.window = new InventoryWindow(skin, ITEM_COUNT, uiMediator, createDragAndDrop(itemDropZone, uiMediator));

        stage.addActor(window);
        stage.addListener(new ToggleWindowListener(window, Input.Keys.I));
    }

    private DragAndDrop createDragAndDrop(Table itemDropZone, Mediator uiMediator){
        DragAndDrop dragAndDrop = new DragAndDrop();
        dragAndDrop.setDragTime(MIN_DRAG_TIME_IN_MILLISECONDS);
        dragAndDrop.addTarget(new DropAreaTarget(itemDropZone, uiMediator));

        return dragAndDrop;
    }

    public void updateItems(List<Item> items){
        window.update(items);
    }

    @Override
    public Table get() {
        return this.window;
    }
}