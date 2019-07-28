package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.factory.TextureFactory;
import com.facundolinlaud.supergame.model.item.Rarity;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.ui.model.equipment.Equipable;
import com.sun.xml.internal.ws.util.StringUtils;

import static com.facundolinlaud.supergame.ui.view.utils.Themes.Colors.BLUE;
import static com.facundolinlaud.supergame.ui.view.utils.Themes.Colors.GRAY;
import static com.facundolinlaud.supergame.ui.view.utils.Themes.Label.SMALL_CAPS_12;
import static com.facundolinlaud.supergame.ui.view.utils.Themes.Label.SMALL_CAPS_14;

public class ItemToolTipContent extends Table {
    private static final int EQUIPMENT_ICON_SIZE = 18;

    private Skin skin;

    public ItemToolTipContent(Item item, Skin skin) {
        super(skin);
        this.skin = skin;

        initializeDefaults();
        addItemNameLabel(item);

        if (item.isEquipable())
            addEquipmentInformation(item);

        addMarketValue(item);
    }

    private void initializeDefaults() {
        setBackground("gothic-tooltip");
        pad(10);
    }

    private void addItemNameLabel(Item item) {
        String title = item.getName() + " (" + item.getRarity().toString().toLowerCase() + ")";
        Label name = new Label(title, skin, SMALL_CAPS_14);
        name.setColor(resolveRarityColor(item.getRarity()));
        add(name).left().colspan(3);
    }

    private void addEquipmentInformation(Item item) {
        Equipable eq = item.getEquipable();

        row().left().padTop(5);
        Label equipmentSlot = new Label(resolveEquipSlot(eq), skin, SMALL_CAPS_12);
        equipmentSlot.setColor(GRAY);
        add(equipmentSlot).align(Align.left).expandX().left().colspan(3);

        row().left().padTop(5);
        Image sword = new Image(skin, "weapon-placeholder");
        add(sword).size(EQUIPMENT_ICON_SIZE, EQUIPMENT_ICON_SIZE).padRight(3);

        Label attack = new Label("attack: " + eq.getAttack(), skin, SMALL_CAPS_12);
        attack.setColor(GRAY);
        add(attack).expandX().left().colspan(2);

        row().align(Align.left).padTop(5);
        Image shield = new Image(skin, "shield-placeholder");
        add(shield).size(EQUIPMENT_ICON_SIZE, EQUIPMENT_ICON_SIZE).left().padRight(4);

        Label defense = new Label("defense: " + eq.getDefense(), skin, SMALL_CAPS_12);
        defense.setColor(GRAY);
        add(defense).expandX().left();
    }

    private void addMarketValue(Item item) {
        Image coins = new Image(TextureFactory.getRegion("pictures/items/others/coins.png"));
        add(coins).size(24, 24).align(Align.right).right();

        Label marketValue = new Label(String.valueOf(item.getMarketValue()), skin, SMALL_CAPS_12);
        add(marketValue).right();
    }

    public static Color resolveRarityColor(Rarity rarity) {
        switch (rarity) {
            case COMMON:
                return Color.WHITE;
            case UNCOMMON:
                return Color.GREEN;
            case RARE:
                return BLUE;
            case EPIC:
                return Color.ORANGE;
            default:
                return Color.WHITE;
        }
    }

    public static String resolveEquipSlot(Equipable eq) {
        return StringUtils.capitalize(eq.getEquipSlot().toString().toLowerCase());
    }
}
