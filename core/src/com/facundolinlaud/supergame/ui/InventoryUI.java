package com.facundolinlaud.supergame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.utils.mediator.Mediator;
import com.facundolinlaud.supergame.utils.mediator.Messenger;
import com.facundolinlaud.supergame.utils.mediator.events.ItemDroppedEvent;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUI implements UI, Messenger {
    public static final String DEFAULT_THEME = "rpg";
    public static final String WINDOW_TITLE = "Inventory";

    private boolean visible;

    private Skin skin;
    private Stage stage;
    private Window window;
    private List itemsList;
    private Table itemDropZone;
    private Mediator uiMediator;

    public InventoryUI(Mediator uiMediator, Stage stage, Skin skin, Table itemDropZone) {
        this.skin = skin;
        this.stage = stage;
        this.itemDropZone = itemDropZone;
        this.uiMediator = uiMediator;

        itemsList = createList();
        ScrollPane scrollPane = createScrollPane();
        window = createWindow();
        window.add(scrollPane).expand().fill();

        initializeListeners();
    }

    private List createList(){
        return new List(skin, DEFAULT_THEME);
    }

    private ScrollPane createScrollPane(){
        ScrollPane scrollPane = new ScrollPane(itemsList, skin);
        scrollPane.setScrollBarPositions(true, true);
        scrollPane.setFlickScroll(false);

        return scrollPane;
    }

    private Window createWindow(){
        float width = 200f;
        float height = 400f;

        Window window = new Window(WINDOW_TITLE, this.skin, DEFAULT_THEME);
        window.getTitleTable().center().top().padLeft(30); // temporal
        window.setPosition(Gdx.graphics.getWidth() - width, Gdx.graphics.getHeight() - height);
        window.setSize(width, height);

        return window;
    }

    private void initializeListeners() {
        DragAndDrop dnd = new DragAndDrop();
        dnd.setDragTime(10);
        configureDragAndDropSource(dnd);
        configureDragAndDropTarget(dnd);

        this.stage.addListener(new InventoryInputListener());
    }

    private void configureDragAndDropTarget(DragAndDrop dnd) {
        dnd.addTarget(new DragAndDrop.Target(itemDropZone) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                return true;
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                int index = (int) payload.getObject();
                uiMediator.raise(InventoryUI.this, ItemDroppedEvent.class, new ItemDroppedEvent(index));
                itemsList.setSelectedIndex(index - 1);
            }
        });
    }

    private void configureDragAndDropSource(DragAndDrop dnd) {
        dnd.addSource(new DragAndDrop.Source(itemsList) {
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                int index = itemsList.getSelectedIndex();

                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setDragActor(new Label(String.valueOf(index), skin));
                payload.setObject(index);

                return payload;
            }
        });
    }

    public void updateItems(Object[] items){
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

    private class InventoryInputListener extends InputListener {
        @Override
        public boolean keyDown(InputEvent event, int keycode) {
            if(Input.Keys.I == keycode)
                setVisible(!isVisible());

            return super.keyDown(event, keycode);
        }
    }
}