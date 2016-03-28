package com.facundolinlaud.supergame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.facundolinlaud.supergame.utils.observer.Observable;
import com.facundolinlaud.supergame.utils.observer.events.ItemDroppedEvent;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUI extends Observable implements UI {
    public static final String DEFAULT_THEME = "rpg";
    public static final String WINDOW_TITLE = "Inventory";

    private boolean visible;

    private Stage stage;
    private Window window;
    private List itemsList;

    public InventoryUI(Stage stage, Skin skin) {
        this.stage = stage;

        itemsList = new List(skin, DEFAULT_THEME);
        itemsList.addListener(new ItemClickListener());

        ScrollPane scrollPane = new ScrollPane(itemsList, skin);
        scrollPane.setScrollBarPositions(true, true);
        scrollPane.setFlickScroll(false);

        float width = 200f;
        float height = 400f;

        window = new Window(WINDOW_TITLE, skin, DEFAULT_THEME);
        window.getTitleTable().center().top().padLeft(30); // temporal
        window.setPosition(Gdx.graphics.getWidth() - width, Gdx.graphics.getHeight() - height);
        window.setSize(width, height);
        window.setDebug(true);
        window.add(scrollPane).expand().fill();

        stage.addListener(new InventoryInputListener());
    }

    public void update(Object[] items){
        itemsList.setItems(items);
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

    private class ItemClickListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            int itemIndex = itemsList.getSelectedIndex();

            if(itemIndex >= 0) {
                notifyObservers(ItemDroppedEvent.class, new ItemDroppedEvent(itemIndex));
                itemsList.setSelectedIndex(itemIndex - 1);
            }
        }
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