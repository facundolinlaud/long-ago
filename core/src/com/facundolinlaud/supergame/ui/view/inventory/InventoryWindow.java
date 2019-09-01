package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.factory.TextureFactory;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.GothicWindow;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

import java.util.List;

/**
 * Created by facundo on 3/30/16.
 */
public class InventoryWindow extends GothicWindow {
    private static final String TITLE = "Inventory";
    private static final int ITEMS_PER_ROW = 8;

    private InventoryGrid grid;
    private Table goldTable;
    private Label goldLabel;
    private Image goldImage;

    public InventoryWindow(Skin skin, int maxItemsAmount, DragAndDrop dragAndDrop) {
        super(TITLE, skin, Themes.Background.DARK);
        setVisible(false);
        adjustSize(maxItemsAmount);
        initializeGrid(skin, maxItemsAmount, dragAndDrop);
        initializeGold(skin);
    }

    private void adjustSize(int itemCount){
        float width = ITEMS_PER_ROW * (InventoryGrid.SLOT_SIZE + InventoryGrid.SLOT_PADDING * 2)
                + getPadLeft() + getPadRight() + 2 * InventoryGrid.GRID_PADDING;
        float height = (float) (Math.ceil((double) itemCount / ITEMS_PER_ROW) *
                (InventoryGrid.SLOT_SIZE + InventoryGrid.SLOT_PADDING * 2)) + getPadTop() + getPadBottom()
                + 2 * InventoryGrid.GRID_PADDING + 30;

        setSize(width, height);
    }

    private void initializeGrid(Skin skin, int maxItemsAmount, DragAndDrop dragAndDrop) {
        grid = new InventoryGrid(skin, ITEMS_PER_ROW, maxItemsAmount, dragAndDrop);
        add(grid).top();
    }

    private void initializeGold(Skin skin) {
        goldTable = new Table(skin);
        goldLabel = new Label("0", skin, Themes.Label.REGULAR_14);
        goldImage = new Image(TextureFactory.getRegion("pictures/items/others/coins.png"));
        goldTable.add(goldLabel).align(Align.right);
        goldTable.add(goldImage).align(Align.right);
        row();
        add(goldTable).right();
    }

    public void update(List<Item> items, int gold){
        this.grid.update(items);
        this.goldLabel.setText(String.valueOf(gold));
    }
}
