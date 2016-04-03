package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.Slot;

/**
 * Created by facundo on 3/29/16.
 */
public class InventorySlot extends Slot {
    private static final int HEIGHT = 32;
    private static final int WIDTH = 32;

    private Item item;
    private Image itemImage;
    private NullItemSlot nullItemSlot;

    public InventorySlot(Skin skin) {
        this.nullItemSlot = new NullItemSlot(skin);

        setSize(WIDTH, HEIGHT);
        add(nullItemSlot);
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
        if(this.itemImage != null) removeActor(this.itemImage);
    }
}
