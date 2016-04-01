package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.utils.SlotType;

/**
 * Created by facundo on 3/31/16.
 */
public class EquipableComponent implements Component {
    public SlotType slotType;

    public EquipableComponent(SlotType slotType) {
        this.slotType = slotType;
    }
}
