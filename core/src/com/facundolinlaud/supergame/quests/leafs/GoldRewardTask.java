package com.facundolinlaud.supergame.quests.leafs;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.components.player.BagComponent;
import com.facundolinlaud.supergame.quests.Blackboard;
import com.facundolinlaud.supergame.quests.Task;
import com.facundolinlaud.supergame.utils.Debugger;
import com.facundolinlaud.supergame.utils.Mappers;

public class GoldRewardTask extends Task {
    private ComponentMapper<BagComponent> bm = Mappers.bag;

    private int gold;

    public GoldRewardTask(int gold) {
        this.gold = gold;
    }

    @Override
    public void activate() {
        Debugger.debug("[GOLD] Activating");
        reward();
        completed();
    }

    @Override
    public void completed() {
        Debugger.debug("[GOLD] Completed");
        super.completed();
    }

    public void reward() {
        Debugger.debug("[GOLD] Rewarding " + gold);
        Entity player = getBlackboard().getPlayer();
        bm.get(player).addGold(this.gold);
    }
}
