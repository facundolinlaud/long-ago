package com.facundolinlaud.supergame.ui;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.facundolinlaud.supergame.components.ItemComponent;
import com.facundolinlaud.supergame.helper.Mappers;

import java.util.stream.Collectors;

/**
 * Created by facundo on 3/26/16.
 */
public class InventoryUI extends UI {
    public static final String DEFAULT_THEME = "default";

    private ComponentMapper<ItemComponent> im = Mappers.item;
    private List itemsList;

    public InventoryUI(Table root, Skin skin) {
        super(root, skin);

        int width = 400;
        int height = 600;

        Table table = new Table(skin);
        table.setWidth(width);
        table.setHeight(height);
        table.setPosition(root.getWidth() - width, root.getHeight() - height);

        itemsList = new List(skin, DEFAULT_THEME);
        itemsList.setFillParent(true);
        itemsList.clearItems();

        table.add(itemsList);
        root.add(table).right();
    }

    public void update(java.util.List<Entity> entities){
        if(entities.size() != itemsList.getItems().size){
            itemsList.clearItems();
            itemsList.setItems(entities.stream().map(e -> im.get(e).name).collect(Collectors.toList()));
        }
    }
}
