package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import com.facundolinlaud.supergame.utils.mediator.Mediator;

import java.util.List;

/**
 * Created by facundo on 3/30/16.
 */
public class InventoryWindow extends Window {
    public static final String TITLE = "Inventory";

    private Grid grid;

    public InventoryWindow(Skin skin, int maxItemsAmount, Mediator uiMediator, DragAndDrop dragAndDrop) {
        super(TITLE, skin, Themes.CLASSIC.toString());

        setVisible(false);
        adjustTitlePosition();
        adjustSize(maxItemsAmount);
        initializeGrid(skin, maxItemsAmount, uiMediator, dragAndDrop);
    }

    private void adjustTitlePosition() {
        getTitleTable().center().top().padLeft(90);
    }

    private void adjustSize(int itemCount){
        float width = Grid.ITEMS_PER_ROW * 50 + 70;
        float height = (float) (Math.ceil((double) itemCount / Grid.ITEMS_PER_ROW) * 50) + 80;

        setSize(width, height);
    }

    private void adjustPosition(){
        setPosition(Gdx.graphics.getWidth() - getWidth(), Gdx.graphics.getHeight() - getHeight());
    }

    private void initializeGrid(Skin skin, int maxItemsAmount, Mediator uiMediator, DragAndDrop dragAndDrop) {
        grid = new Grid(skin, maxItemsAmount, uiMediator, dragAndDrop);
        add(grid);
    }

    public void update(List<Item> items){
        grid.update(items);
    }
}
