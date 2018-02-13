package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.SlotSource;
import com.facundolinlaud.supergame.ui.view.cross.SlotType;
import com.facundolinlaud.supergame.model.EquipSlot;
import com.facundolinlaud.supergame.utils.mediator.Mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 4/2/16.
 */
public class Grid extends Table {

    private Map<EquipSlot, EquipmentSlot> slots;

    public Grid(Skin skin, Mediator uiMediator, DragAndDrop dragAndDrop) {
        super(skin);

        this.slots = new HashMap<>();

        align(Align.topLeft);

        EquipSlot equipSlot[][] = getDistribution();

        for(int row = 0; row < equipSlot.length; row++){
            for(int col = 0; col < equipSlot[row].length; col++){
                EquipSlot wt = equipSlot[row][col];

                boolean shouldAddSlotHere = wt != null;

                EquipmentSlot slot = new EquipmentSlot(skin, wt);
                slot.setVisible(shouldAddSlotHere);

                dragAndDrop.addSource(new SlotSource(slot, skin, SlotType.EQUIPMENT_SLOT));
                dragAndDrop.addTarget(new EquipmentSlotTarget(slot, uiMediator, wt));

                if(shouldAddSlotHere) slots.put(wt, slot);
                add(slot).pad(5);
            }

            row();
        }
    }

    private EquipSlot[][] getDistribution(){
        return new EquipSlot[][]{
                {null, EquipSlot.HELMET, null},
                {EquipSlot.WEAPON, EquipSlot.CHEST, EquipSlot.SHIELD},
                {EquipSlot.GLOVES, EquipSlot.PANTS, EquipSlot.SHOES}
        };
    }

    public void update(Map<EquipSlot, Item> items) {
        for(EquipSlot wt : slots.keySet()){
            Item item = items.get(wt);

            EquipmentSlot slot = slots.get(wt);
            slot.clearItem();

            if(item != null){
                slot.setItem(item);
            }
        }
    }
}
