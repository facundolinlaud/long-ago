package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.ItemToolTipContent;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 3/29/16.
 */
public class InventorySlot extends Slot<Item> {
    public static final float SIZE = 42;

    private Item item;
    private Skin skin;
    private Image itemImage;
    private ImageButton slotButton;
    private Tooltip<ItemToolTipContent> tooltip;

    public InventorySlot(Skin skin) {
        this.slotButton = new ImageButton(skin, Themes.IMAGE_BUTTON_SLOT);
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
        buildToolTip(item);
        this.item = item;
    }

    private void updatePicture(Item item) {
        Sprite sprite = item.getPicture();
        Drawable drawable = new TextureRegionDrawable(sprite);
        this.itemImage.setDrawable(drawable);
    }

    private void buildToolTip(Item item) {
        if(this.tooltip != null)
            removeListener(this.tooltip);

        ItemToolTipContent itemToolTipContent = new ItemToolTipContent(skin, item);
        this.tooltip = new Tooltip(itemToolTipContent);
//        this.tooltip.setInstant(true);
        addListener(this.tooltip);
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