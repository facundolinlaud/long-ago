package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.Slot;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 4/2/16.
 */
public class EquipmentSlot extends Slot<Item> {
    public static final int SIZE = 42;

    private Item item;
    private Skin skin;
    private EquipSlot equipSlot;
    private ImageButton imageButton;
    private ImageButton.ImageButtonStyle style;

    public EquipmentSlot(Skin skin, EquipSlot equipSlot) {
        this.skin = skin;
        this.equipSlot = equipSlot;
        this.imageButton = new ImageButton(skin, Themes.IMAGE_BUTTON_SLOT);

        this.style = new ImageButton.ImageButtonStyle();
        this.style.up = imageButton.getStyle().up;
        this.imageButton.setStyle(style);

        add(this.imageButton);
    }

    public void allowPlaceHolder(){
        this.style.imageUp = skin.getDrawable(equipSlot.toString().toLowerCase() + "-placeholder");
    }

    @Override
    public void setContent(Item item){
        Sprite sprite = item.getPicture();
        Drawable spriteDrawable  = new TextureRegionDrawable(sprite);

        this.style.imageChecked = spriteDrawable;
        this.imageButton.setChecked(true);
        this.item = item;
    }

    @Override
    public Item getContent() {
        return item;
    }

    @Override
    public void clearContent(){
        if(this.item != null){
            this.imageButton.getStyle().imageChecked = null;
            this.imageButton.setChecked(false);
            this.item = null;
        }
    }
}
