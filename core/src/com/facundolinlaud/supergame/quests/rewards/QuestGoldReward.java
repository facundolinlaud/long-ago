package com.facundolinlaud.supergame.quests.rewards;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.utils.Mappers;

public class QuestGoldReward implements QuestReward {
    private ComponentMapper<BagComponent> bm = Mappers.bag;

    private Entity player;
    private int gold;

    public QuestGoldReward(Entity player, int gold) {
        this.player = player;
        this.gold = gold;
    }

    @Override
    public void reward() {
        bm.get(this.player).addGold(this.gold);
    }
}
