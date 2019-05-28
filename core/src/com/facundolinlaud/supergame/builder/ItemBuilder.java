package com.facundolinlaud.supergame.builder;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.items.PickupableComponent;
import com.facundolinlaud.supergame.components.sprite.StackableSpriteComponent;
import com.facundolinlaud.supergame.factory.TextureFactory;
import com.facundolinlaud.supergame.model.RenderPriority;
import com.facundolinlaud.supergame.model.item.EquipmentInformation;

public class ItemBuilder {
    private Entity item;

    public ItemBuilder(String name, String picture) {
        this.item = new Entity().add(new ItemComponent(name, picture));
    }

    public ItemBuilder withRender(String picture, RenderPriority priority){
        this.item.add(new RenderComponent(new TextureRegion(TextureFactory.get(picture)), priority));
        return this;
    }

    public ItemBuilder pickupable(){
        item.add(new PickupableComponent());
        return this;
    }

    public ItemBuilder withPosition(float x, float y){
        item.add(new PositionComponent(x, y));
        return this;
    }

    public ItemBuilder equipable(String spritesheet, EquipmentInformation eq){
        item.add(new EquipableComponent(eq))
            .add(new StackableSpriteComponent(TextureFactory.get(spritesheet)));
        return this;
    }

    public ItemBuilder add(Component component) {
        item.add(component);
        return this;
    }

    public ItemBuilder dropped(float x, float y){
        return pickupable().withPosition(x, y);
    }

    public Entity build(){
        return item;
    }
}
