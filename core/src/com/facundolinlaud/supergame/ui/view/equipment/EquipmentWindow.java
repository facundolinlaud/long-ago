package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.GothicWindow;

import java.util.Map;

/**
 * Created by facundo on 4/2/16.
 */
public class EquipmentWindow extends GothicWindow {
    public static final String TITLE = "Equipment";

    private EquipmentGrid grid;

    public EquipmentWindow(Skin skin, DragAndDrop dragAndDrop) {
        super(TITLE, skin);
        setVisible(false);
        setSize(265, 320);
        initializeGrid(skin, dragAndDrop);
        setDebug(true);
    }

    private void initializeGrid(Skin skin, DragAndDrop dragAndDrop) {
        this.grid = new EquipmentGrid(skin, dragAndDrop);
        add(this.grid).fill().expand();
    }

    public void update(Map<EquipSlot, Item> items){
        if(isVisible())
            grid.update(items);
    }
}
