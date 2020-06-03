package com.facundolinlaud.supergame.services;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.components.sprite.RefreshSpriteRequirementComponent;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.utils.Mappers;

public class EquipmentService extends Service {
    private ComponentMapper<WearComponent> wm = Mappers.wear;
    private ComponentMapper<EquipableComponent> em = Mappers.equipable;

    private AgentService agentService;

    public EquipmentService(Engine engine, AgentService agentService) {
        super(engine);
        this.agentService = agentService;
    }

    public void equip(Entity equipment) {
        EquipableComponent equipableComponent = em.get(equipment);
        WearComponent wearComponent = getPlayerEquipment();
        wearComponent.wearables.put(equipableComponent.getEquipSlot(), equipment);

        refreshEquipperSprite();
    }

    public Entity unequip(EquipSlot equipSlot) {
        WearComponent wearComponent = getPlayerEquipment();
        Entity equipment = wearComponent.wearables.remove(equipSlot);

        refreshEquipperSprite();
        return equipment;
    }

    public WearComponent getPlayerEquipment() {
        return wm.get(agentService.getPlayer());
    }

    private void refreshEquipperSprite(){
        agentService.getPlayer().add(new RefreshSpriteRequirementComponent());
    }
}
