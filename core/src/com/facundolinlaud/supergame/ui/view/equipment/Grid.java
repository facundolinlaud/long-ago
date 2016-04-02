package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.utils.WearType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 4/2/16.
 */
public class Grid extends Table {

    private Map<WearType, Slot> slots;

    public Grid(Skin skin) {
        super(skin);

        this.slots = new HashMap<>();

        align(Align.topLeft);

        WearType wearType[][] = getDistribution();

        for(int row = 0; row < wearType.length; row++){
            for(int col = 0; col < wearType[row].length; col++){
                WearType wt = wearType[row][col];

                boolean shouldAddSlotHere = wt != null;

                Slot slot = new Slot(skin, wt);
                slot.setVisible(shouldAddSlotHere);

                if(shouldAddSlotHere) slots.put(wt, slot);
                add(slot).pad(5);
            }

            row();
        }
    }

    private WearType[][] getDistribution(){
        return new WearType[][]{
                {null, WearType.HELMET, null},
                {WearType.WEAPON, WearType.CHEST, WearType.SHIELD},
                {WearType.GLOVES, WearType.PANTS, WearType.SHOES}
        };
    }

    public void update(Map<WearType, Item> items) {
        for(WearType wt : slots.keySet()){
            Item item = items.get(wt);

            Slot slot = slots.get(wt);
            slot.clearItem();

            if(item != null){
                slot.setItem(item);
            }
        }

//        slots.values().stream().forEach(s -> s.clearItem());
//        items.keySet().stream().forEach(wt -> slots.get(wt).setItem(items.get(wt)));

//        for(WearType wt : items.keySet()){
//            slots.get(wt).setItem(items.get(wt));
//        }
    }
}
