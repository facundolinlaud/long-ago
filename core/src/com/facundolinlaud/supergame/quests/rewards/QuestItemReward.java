package com.facundolinlaud.supergame.quests.rewards;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.List;

public class QuestItemReward implements QuestReward {
    private ComponentMapper<BagComponent> bm = Mappers.bag;

    private Entity player;
    private List<Entity> items;

    public QuestItemReward(Entity player, List<Entity> items) {
        this.player = player;
        this.items = items;
    }

    @Override
    public void reward() {
        System.out.println("Rewarding player");
        BagComponent bagComponent = bm.get(player);
        items.forEach(item -> bagComponent.add(item));
    }
}
