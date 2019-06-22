package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.GothicWindow;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import com.facundolinlaud.supergame.utils.mediator.Mediator;

import java.util.List;

/**
 * Created by facundo on 3/30/16.
 */
public class InventoryWindow extends GothicWindow {
    private static final String TITLE = "Inventory";
    private static final int ITEMS_PER_ROW = 8;

    private Grid grid;

    public InventoryWindow(Skin skin, int maxItemsAmount, Mediator uiMediator, DragAndDrop dragAndDrop) {
        super(TITLE, skin);
        setVisible(false);
        adjustSize(maxItemsAmount);
        initializeGrid(skin, maxItemsAmount, uiMediator, dragAndDrop);
    }

    private void adjustSize(int itemCount){
        float width = ITEMS_PER_ROW * InventorySlot.SIZE + getPadLeft() + getPadRight();
        float height = (float) (Math.ceil((double) itemCount / ITEMS_PER_ROW) * InventorySlot.SIZE)
                + getPadTop() + getPadBottom();

        setSize(width, height);
    }

    private void initializeGrid(Skin skin, int maxItemsAmount, Mediator uiMediator, DragAndDrop dragAndDrop) {
        this.grid = new Grid(skin, ITEMS_PER_ROW, maxItemsAmount, uiMediator, dragAndDrop);
        this.add(grid);
    }

    public void update(List<Item> items){
        if(isVisible())
            this.grid.update(items);
    }
}
