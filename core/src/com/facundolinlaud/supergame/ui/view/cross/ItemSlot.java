package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Tooltip;
import com.facundolinlaud.supergame.ui.model.Item;

public abstract class ItemSlot extends Slot<Item> {
    private Tooltip<ItemToolTipContent> tooltip;

    protected void buildToolTip(Item item, Skin skin) {
        if(this.tooltip != null)
            removeListener(this.tooltip);

        ItemToolTipContent itemToolTipContent = new ItemToolTipContent(item, skin);
        this.tooltip = new Tooltip(itemToolTipContent);
        addListener(this.tooltip);
    }
}
