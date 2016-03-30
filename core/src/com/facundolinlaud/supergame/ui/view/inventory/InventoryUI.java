package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.UI;
import com.facundolinlaud.supergame.utils.mediator.Mediator;
import com.facundolinlaud.supergame.utils.mediator.Messenger;

import java.util.List;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUI implements UI, Messenger {
    private static final int ITEM_COUNT = 17;

    private boolean visible;

    private Stage stage;
    private InventoryWindow window;
    private Table itemDropZone;
    private Mediator uiMediator;
    private DragAndDrop dragAndDrop;

    public InventoryUI(Mediator uiMediator, Stage stage, Skin skin, Table itemDropZone) {
        this.stage = stage;
        this.itemDropZone = itemDropZone;
        this.uiMediator = uiMediator;

        this.dragAndDrop = new DragAndDrop();
        this.dragAndDrop.setDragTime(10);
        this.dragAndDrop.addTarget(new DropAreaTarget(itemDropZone, uiMediator));

        this.window = new InventoryWindow(skin, ITEM_COUNT, uiMediator, dragAndDrop);

        this.stage.addListener(new InventoryInputListener());
    }

    public void updateItems(List<Item> items){
        window.update(items);
    }

    @Override
    public Table getUI() {
        return this.window;
    }

    public void setVisible(boolean state) {
        if(this.visible != state){
            if(state) {
                this.stage.addActor(window);
            } else {
                this.stage.addAction(Actions.removeActor(window));
            }
        }

        this.visible = state;
    }

    public boolean isVisible() {
        return visible;
    }

    private class InventoryInputListener extends InputListener {
        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            if(Input.Keys.I == keycode)
               setVisible(!isVisible());

            return super.keyDown(event, keycode);
        }
    }
}