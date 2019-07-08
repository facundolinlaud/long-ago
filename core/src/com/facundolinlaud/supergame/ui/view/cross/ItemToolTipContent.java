package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.model.equipment.Equipable;

public class ItemToolTipContent extends Table {
    private Label name;
    private Label attack;
    private Label defense;

    public ItemToolTipContent(Skin skin, Item item) {
        super(skin);
        setBackground("gothic-tooltip");
        pad(10);

        this.name = new Label(item.getName(), skin, "gothic-white");
        add(this.name).left().padBottom(5);

        if(item.isEquipable()){
            Equipable eq = item.getEquipable();
            this.attack = new Label("Attack: " + eq.getAttack(), skin, "gothic-white");
            this.defense = new Label("Defense: " + eq.getDefense(), skin, "gothic-white");

            row();
            add(this.attack).left().padBottom(5);
            row();
            add(this.defense).left();
        }
    }
}
