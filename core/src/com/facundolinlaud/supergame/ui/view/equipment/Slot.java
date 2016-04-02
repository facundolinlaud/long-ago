package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.utils.WearType;

/**
 * Created by facundo on 4/2/16.
 */
public class Slot extends Stack {
    private static final int HEIGHT = 32;
    private static final int WIDTH = 32;

    private NullEquipmentSlot nullEquipmentSlot;
    private Image itemImage;
    private Item item;
    private WearType wearType;

    public Slot(Skin skin, WearType wearType) {
        this.wearType = wearType;
        this.nullEquipmentSlot = new NullEquipmentSlot(skin);
        this.itemImage = new Image();

        setSize(WIDTH, HEIGHT);
        add(this.nullEquipmentSlot);
    }

    public void setItem(Item item){
        this.item = item;
        this.itemImage = new Image(item.getPicture());

        add(this.itemImage);
    }

    public Item getItem() {
        return item;
    }

    public void clearItem(){
        if(this.itemImage != null) removeActor(this.itemImage);
    }
}
