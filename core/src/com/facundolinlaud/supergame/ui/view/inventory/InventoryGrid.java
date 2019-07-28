package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.ItemSlotSource;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.SlotType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by facundo on 3/29/16.
 */
public class InventoryGrid extends Table {
    public static final float SLOT_SIZE = 42;
    public static final int SLOT_PADDING = 2;
    public static final float GRID_PADDING = 4;

    private List<InventorySlot> slots;

    public InventoryGrid(Skin skin, int itemsPerRow, int itemsAmount, DragAndDrop dragAndDrop) {
        super(skin);

        this.slots = new ArrayList<>();
        this.pad(GRID_PADDING);

        for(int i = 1; i <= itemsAmount; i++){
            InventorySlot slot = new InventorySlot(skin);

            add(slot).size(SLOT_SIZE, SLOT_SIZE).pad(SLOT_PADDING);
            slots.add(slot);
            dragAndDrop.addSource(new ItemSlotSource(slot, SlotType.INVENTORY_SLOT));
            dragAndDrop.addTarget(new InventorySlotTarget(slot));

            if(i % itemsPerRow == 0)
                row();
        }
    }

    public void update(List<Item> items){
        /* If building MAX_ITEMS tooltips on each refresh gets too heavy, I can use the ObservableList change event
           to delete the specific removed item */

        slots.stream().forEach(InventorySlot::clearContent);

        for(int i = 0; i < items.size(); i++){
            if(i < slots.size()){
                slots.get(i).setContent(items.get(i));
            }else{
                System.out.println("No space in inventory UI");
            }
        }
    }
}
