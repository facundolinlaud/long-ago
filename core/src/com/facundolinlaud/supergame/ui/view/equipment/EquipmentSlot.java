package com.facundolinlaud.supergame.ui.view.equipment;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.view.cross.ItemSlot;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 4/2/16.
 */
public class EquipmentSlot extends ItemSlot {
    public static final int SIZE = 42;

    private Item item;
    private Skin skin;
    private EquipSlot equipSlot;
    private ImageButton imageButton;
    private ImageButton.ImageButtonStyle style;

    public EquipmentSlot(Skin skin, EquipSlot equipSlot) {
        this.skin = skin;
        this.equipSlot = equipSlot;
        this.imageButton = new ImageButton(skin, Themes.ImageButton.THREE_D_SLOT);

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
        if(this.item == item)
            return;

        updatePicture(item);
        buildToolTip(item, skin);
        this.item = item;
    }

    private void updatePicture(Item item) {
        Sprite sprite = item.getPicture();
        Drawable drawable = new TextureRegionDrawable(sprite);

        this.style.imageChecked = drawable;
        this.imageButton.setChecked(true);
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
