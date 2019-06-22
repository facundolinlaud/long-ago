package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 3/29/16.
 */
public class InventorySlot extends Slot<Item> {
    public static final float SIZE = 42;

    private Item item;
    private Image itemImage;
    private ImageButton slotButton;

    public InventorySlot(Skin skin) {
        this.slotButton = new ImageButton(skin, Themes.IMAGE_BUTTON_SLOT);
        add(slotButton);
    }

    @Override
    public void setContent(Item item){
        this.item = item;
        this.itemImage = new Image(item.getPicture());
        add(this.itemImage);
    }

    @Override
    public Item getContent() {
        return item;
    }

    @Override
    public void clearContent(){
        if(this.item != null){
            removeActor(this.itemImage);
            this.item = null;
        }
    }
}
