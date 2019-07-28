package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.equipment.EquipmentWindow;

import java.util.Map;

/**
 * Created by facundo on 4/2/16.
 */
public class EquipmentUI implements UI {
    private EquipmentWindow window;

    public EquipmentUI(Skin skin, DragAndDrop dragAndDrop) {
        this.window = new EquipmentWindow(skin, dragAndDrop);
    }

    public void update(Map<EquipSlot, Item> items) {
        this.window.update(items);
    }

    @Override
    public Table get() {
        return window;
    }
}
