package com.facundolinlaud.supergame.behaviortree.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.model.equip.EquipSlot;
import com.facundolinlaud.supergame.model.equip.EquipType;
import com.facundolinlaud.supergame.utils.Mappers;

import static com.facundolinlaud.supergame.utils.events.Messages.REJECTED_SKILL_DUE_TO_WEAPON;

/**
 * Pops: nothing
 * Pushes: nothing
 */
public class RequireEquipmentTask extends LeafTask<Blackboard> {
    private ComponentMapper<WearComponent> wm = Mappers.wear;
    private ComponentMapper<EquipableComponent> em = Mappers.equipable;

    private EquipType equipType;
    private MessageDispatcher messageDispatcher;

    public RequireEquipmentTask(EquipType equipType) {
        this.messageDispatcher = MessageManager.getInstance();
        this.equipType = equipType;
    }

    @Override
    public void activate() {
        if (hasAdequateWeapon())
            completed();
        else {
            messageDispatcher.dispatchMessage(REJECTED_SKILL_DUE_TO_WEAPON);
            failed();
        }
    }

    private boolean hasAdequateWeapon() {
        Entity agent = getBlackboard().getAgent();
        WearComponent wearComponent = wm.get(agent);

        if (EquipType.BOW.equals(equipType)) {
            return wearComponent.hasWearable(EquipSlot.BOW);
        } else if (wearComponent.hasWearable(EquipSlot.WEAPON)) {
            Entity weapon = wearComponent.getWearable(EquipSlot.WEAPON);
            EquipableComponent eq = em.get(weapon);

            return eq.getEquipType().equals(equipType);
        }

        return false;
    }
}
