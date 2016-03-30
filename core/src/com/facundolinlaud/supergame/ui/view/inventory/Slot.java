package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.facundolinlaud.supergame.ui.model.Item;

/**
 * Created by facundo on 3/29/16.
 */
public class Slot extends Stack {
    private static final int HEIGHT = 32;
    private static final int WIDTH = 32;

    private Item item;
    private Image itemImage;
    private NullItemSlot nullItemSlot;

    public Slot(Skin skin) {
        this.nullItemSlot = new NullItemSlot(skin);

        setSize(WIDTH, HEIGHT);
        add(nullItemSlot);
    }

    public void setItem(Item item){
        this.item = item;
        this.itemImage = new Image(item.getTextureRegion());
        add(this.itemImage);
    }

    public Item getItem() {
        return item;
    }

    public void clearItem(){
        if(this.itemImage != null) removeActor(this.itemImage);
    }
}
