package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.ItemSlot;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 3/29/16.
 */
public class InventorySlot extends ItemSlot {
    public static final float SIZE = 42;

    private Item item;
    private Skin skin;
    private Image itemImage;
    private ImageButton slotButton;

    public InventorySlot(Skin skin) {
        this.slotButton = new ImageButton(skin, Themes.ImageButton.SLOT);
        this.itemImage = new Image();
        this.skin = skin;

        add(this.slotButton);
        add(this.itemImage);
    }

    @Override
    public void setContent(Item item){
        if(this.item == item)
            return;

        updatePicture(item);
        buildToolTip(item, skin);
        this.item = item;
    }

    private void updatePicture(Item item) {
        Sprite sprite = item.getPicture();
        Drawable drawable = new TextureRegionDrawable(sprite);
        this.itemImage.setDrawable(drawable);
    }

    @Override
    public Item getContent() {
        return item;
    }

    @Override
    public void clearContent(){
        if(this.item != null){
            this.itemImage.setDrawable(null);
            this.item = null;
        }
    }
}