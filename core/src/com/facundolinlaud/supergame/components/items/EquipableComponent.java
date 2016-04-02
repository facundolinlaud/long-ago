package com.facundolinlaud.supergame.components.items;

import com.badlogic.ashley.core.Component;
import com.facundolinlaud.supergame.utils.WearType;

/**
 * Created by facundo on 3/31/16.
 */
public class EquipableComponent implements Component {
    public WearType wearType;

    public EquipableComponent(WearType wearType) {
        this.wearType = wearType;
    }
}
