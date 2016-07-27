package com.facundolinlaud.supergame.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.model.WearType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by facundo on 4/1/16.
 */
public class WearComponent implements Component {
    public Map<WearType, Entity> wearables;

    public WearComponent() {
        this.wearables = new HashMap<>();
    }

    public WearComponent(Map<WearType, Entity> wearables) {
        this.wearables = wearables;
    }

    public List<Entity> asList(){
        return wearables.keySet().stream().sorted((e1, e2) -> e1.getRenderPriority() - e2.getRenderPriority()).map(e -> wearables.get(e)).collect(Collectors.toList());
    }
}
