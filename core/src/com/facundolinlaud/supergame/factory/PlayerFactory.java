package com.facundolinlaud.supergame.factory;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.facundolinlaud.supergame.model.entity.ItemModel;
import com.facundolinlaud.supergame.model.entity.PlayerModel;
import com.facundolinlaud.supergame.components.*;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.items.ItemComponent;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.components.sprite.AnimableSpriteComponent;
import com.facundolinlaud.supergame.components.sprite.RefreshSpriteRequirementComponent;
import com.facundolinlaud.supergame.components.sprite.StackableSpriteComponent;
import com.facundolinlaud.supergame.components.sprite.StackedSpritesComponent;
import com.facundolinlaud.supergame.model.RenderPriority;
import com.facundolinlaud.supergame.model.WearType;
import com.facundolinlaud.supergame.utils.strategy.impl.SpriteRenderPositionStrategyImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by facundo on 27/7/16.
 */
public class PlayerFactory {
    public static Entity getPlayerEntity(){
        return createPlayer(ModelFactory.getPlayerModel());
    }

    private static Entity createPlayer(PlayerModel playerModel){
        Entity entity = new Entity()
                .add(new PositionComponent(playerModel.getX(), playerModel.getY()))
                .add(new KeyboardComponent())
                .add(new VelocityComponent(playerModel.getVelocity()))
                .add(new BodyComponent(PhysicsFactory.get().createItemBody()))
                .add(new WearComponent(createWearablesEntities(playerModel)))
                .add(new RenderComponent(new SpriteRenderPositionStrategyImpl()))
                .add(new StatusComponent())
                .add(new AnimableSpriteComponent())
                .add(new StackedSpritesComponent(ModelFactory.getDefaultAnimationModel()))
                .add(new RefreshSpriteRequirementComponent())
                .add(new HealthComponent())
                .add(new BagComponent(createItems(playerModel.getInventory())))
                .add(new AttributesComponent());

        return entity;
    }

    private static Map<WearType, Entity> createWearablesEntities(PlayerModel playerModel){
        Map<WearType, ItemModel> wearablesModels = playerModel.getEquipment();
        Map<WearType, String> bodyModels = playerModel.getBody();
        HashMap<WearType, Entity> wearables = new HashMap<>();

        for(WearType wearType : bodyModels.keySet()){
            String texture = bodyModels.get(wearType);
            wearables.put(wearType, new Entity().add(new StackableSpriteComponent(TextureFactory.getTexture(texture))));
        }

        for(WearType wearType : wearablesModels.keySet()){
            wearables.put(wearType, createItemEntity(wearablesModels.get(wearType)));
        }

        return wearables;
    }

    private static List<Entity> createItems(List<ItemModel> itemsModels){
        return itemsModels.stream().map(e -> createItemEntity(e)).collect(Collectors.toList());
    }

    private static Entity createItemEntity(ItemModel itemModel){
        Entity entity = new Entity();
        entity.add(new ItemComponent(itemModel.getName(), itemModel.getPicture()));

        if(itemModel.isEquipable()) {
            entity
                .add(new EquipableComponent(itemModel.getWearType(), itemModel.getAttack(), itemModel.getDefense()))
                .add(new StackableSpriteComponent(TextureFactory.getTexture(itemModel.getTexture())))
                .add(new RenderComponent(new TextureRegion(TextureFactory.getTexture(itemModel.getPicture())), new RenderPriority(1)));
        }

        return entity;
    }
}
