package com.facundolinlaud.supergame.strategies.stats;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.AttributesComponent;
import com.facundolinlaud.supergame.components.items.EquipableComponent;
import com.facundolinlaud.supergame.components.player.WearComponent;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.utils.Mappers;

public class DefaultDamageLogic implements DamageLogic {
    private static final int MAXIMUM_DEFENSE = 50;
    private static final int MAXIMUM_AGILITY = 50;

    private ComponentMapper<WearComponent> wm = Mappers.wear;
    private ComponentMapper<AttributesComponent> am = Mappers.attributes;
    private ComponentMapper<EquipableComponent> em = Mappers.equipable;

    @Override
    public float calculateDamageDealt(Entity caster, Entity victim, Skill skill) {
        float damageDone = calculateDamageDone(caster, skill);
        return calculateDamageReceived(victim, damageDone);
    }

    private float calculateDamageDone(Entity caster, Skill skill) {
        AttributesComponent attributes = am.get(caster);
        WearComponent equipment = wm.get(caster);

        float equipmentAdditive = 0;
        float attributesAdditive = 0;

        for(Entity item : equipment.getEquipmentAsList()){
            EquipableComponent equipable = em.get(item);
            equipmentAdditive += equipable.getAttack();
        }

        switch(skill.getSkillType()){
            case MELEE:
                attributesAdditive = attributes.getStrength();
                break;
            case SPELL:
                attributesAdditive = attributes.getIntelligence();
                break;
            case PROJECTILE:
                attributesAdditive = (attributes.getStrength() + attributes.getIntelligence()) / 2;
                break;
        }

        return equipmentAdditive + attributesAdditive;
    }

    private float calculateDamageReceived(Entity victim, float damageDone) {
        AttributesComponent attributes = am.get(victim);
        WearComponent equipment = wm.get(victim);

        float equipmentMultiplicator = MAXIMUM_DEFENSE;
        float attributesMultiplicator = MAXIMUM_AGILITY - attributes.getAgility();

        for(Entity item : equipment.getEquipmentAsList()){
            EquipableComponent equipable = em.get(item);
            equipmentMultiplicator -= equipable.getDefense();
        }

        float mitigatedDamage = damageDone * (equipmentMultiplicator / MAXIMUM_DEFENSE) *
                (attributesMultiplicator / MAXIMUM_AGILITY);

        return mitigatedDamage;
    }
}
