package com.facundolinlaud.supergame.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.facundolinlaud.supergame.utils.observer.Observable;
import com.facundolinlaud.supergame.utils.observer.events.InventoryEvent;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUI extends Observable implements UI {
    public static final String DEFAULT_THEME = "default";

    private Table table;
    private List itemsList;

    public InventoryUI(Skin skin) {
        this.table = new Table(skin);

        this.itemsList = new List(skin, DEFAULT_THEME);
        this.itemsList.addListener(new ItemClickListener());

        ScrollPane scrollPane = new ScrollPane(itemsList, skin);
        scrollPane.setScrollBarPositions(true, true);
        scrollPane.setFlickScroll(false);
        this.table.add(scrollPane).expand().fill();
    }

    public void update(Object[] items){
        itemsList.setItems(items);
    }

    @Override
    public Table getUI() {
        return this.table;
    }

    private class ItemClickListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            int selectedIndex = itemsList.getSelectedIndex();

            if(selectedIndex >= 0)
                notifyObservers(InventoryEvent.class, new InventoryEvent(selectedIndex));
        }
    }
}