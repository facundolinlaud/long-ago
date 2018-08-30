package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.model.equip.EquipSlot;

/**
 * Created by facundo on 4/2/16.
 */
public class EquipmentSlot extends Slot {
    private static final int HEIGHT = 32;
    private static final int WIDTH = 32;

    private NullEquipmentSlot nullEquipmentSlot;
    private Image itemImage;
    private Item item;
    private EquipSlot equipSlot;

    public EquipmentSlot(Skin skin, EquipSlot equipSlot) {
        this.equipSlot = equipSlot;
        this.nullEquipmentSlot = new NullEquipmentSlot(skin);
        this.itemImage = new Image();

        setSize(WIDTH, HEIGHT);
        add(this.nullEquipmentSlot);
    }

    @Override
    public void setItem(Item item){
        this.item = item;
        this.itemImage = new Image(item.getPicture());

        add(this.itemImage);
    }

    @Override
    public Item getItem() {
        return item;
    }

    @Override
    public void clearItem(){
        if(this.item != null){
            removeActor(this.itemImage);
            this.item = null;
        }
    }
}
