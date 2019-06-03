package com.facundolinlaud.supergame.builder;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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

    public ItemBuilder(Entity item) {
        this.item = item;
    }

    public ItemBuilder(String name, String picture) {
        TextureRegion region = TextureFactory.getRegion(picture);
        this.item = new Entity().add(new ItemComponent(name, region));
    }

    public ItemBuilder withRender(String picture){
        this.item.add(new RenderComponent(TextureFactory.getRegion(picture), RenderPriority.ITEM));
        return this;
    }

    public ItemBuilder withRender(TextureRegion picture){
        this.item.add(new RenderComponent(picture, RenderPriority.ITEM));
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
            .add(new StackableSpriteComponent(TextureFactory.getTexture(spritesheet)));
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
