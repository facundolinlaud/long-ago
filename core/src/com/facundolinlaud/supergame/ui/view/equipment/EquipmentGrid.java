package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.SlotSource;
import com.facundolinlaud.supergame.ui.view.cross.SlotType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 4/2/16.
 */
public class EquipmentGrid extends Table {

    private Map<EquipSlot, EquipmentSlot> slots;

    public EquipmentGrid(Skin skin, DragAndDrop dragAndDrop) {
        super(skin);

        this.slots = new HashMap<>();

        align(Align.top);

        EquipSlot equipSlot[][] = getDistribution();
        Vector2 dimensionsMultipliers[][] = getDimensionsMultiplier();

        for(int row = 0; row < equipSlot.length; row++){
            for(int col = 0; col < equipSlot[row].length; col++){
                EquipSlot wt = equipSlot[row][col];

                boolean shouldAddSlotHere = wt != null;
                Vector2 scale = dimensionsMultipliers[row][col];
                EquipmentSlot slot = new EquipmentSlot(skin, wt);
                slot.setVisible(shouldAddSlotHere);

                if(shouldAddSlotHere) {
                    slot.allowPlaceHolder();
                    dragAndDrop.addSource(new SlotSource(slot, skin, SlotType.EQUIPMENT_SLOT));
                    dragAndDrop.addTarget(new EquipmentSlotTarget(slot, wt));

                    slots.put(wt, slot);
                }

                add(slot).pad(5).size(EquipmentSlot.SIZE * scale.x, EquipmentSlot.SIZE * scale.y);
            }

            row();
        }
    }

    private Vector2[][] getDimensionsMultiplier(){
        Vector2 oneByOne = new Vector2(1, 1);
        Vector2 oneByTwo = new Vector2(1, 2);
        Vector2 twoByTwo = new Vector2(2, 2);
        Vector2 twoByOne = new Vector2(2, 1);

        return new Vector2[][]{
                {oneByOne, oneByOne, oneByOne},
                {oneByTwo, twoByTwo, oneByTwo},
                {oneByOne, twoByOne, oneByOne},
                {oneByOne, twoByOne, oneByOne}
        };
    }

    private EquipSlot[][] getDistribution(){
        return new EquipSlot[][]{
                {EquipSlot.BOW, EquipSlot.HELMET, EquipSlot.NECKAKLE},
                {EquipSlot.WEAPON, EquipSlot.CHEST, EquipSlot.SHIELD},
                {EquipSlot.GLOVES, EquipSlot.BELT, EquipSlot.RING},
                {null, EquipSlot.PANTS, EquipSlot.SHOES}
        };
    }

    public void update(Map<EquipSlot, Item> items) {
        for(EquipSlot wt : slots.keySet()){
            Item item = items.get(wt);

            EquipmentSlot slot = slots.get(wt);
            slot.clearContent();

            if(item != null){
                slot.setContent(item);
            }
        }
    }
}
