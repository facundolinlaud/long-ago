package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.facundolinlaud.supergame.behaviortree.PoolableTask;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.ui.controller.OverlayUIController;
import com.facundolinlaud.supergame.utils.Mappers;

public class DisplayCastBarTask extends PoolableTask<SkillBlackboard> {
    private ComponentMapper<KeyboardComponent> km = Mappers.keyboard;

    private OverlayUIController overlayUIController;

    private String title;
    private float totalTime;
    private float elapsedTime;
    private boolean foundOutIfMainPlayer;

    public DisplayCastBarTask(String title, float totalTime) {
        this.title = title;
        this.elapsedTime = 0f;
        this.totalTime = totalTime;
        this.foundOutIfMainPlayer = false;
    }

    @Override
    protected void onBlackboardAvailable(SkillBlackboard blackboard) {
        overlayUIController = blackboard.getOverlayUIController();
    }

    @Override
    public void tick(float delta) {
        if (!foundOutIfMainPlayer) {
            Entity caster = getBlackboard().getCaster();
            if (isMainPlayer(caster)) {
                foundOutIfMainPlayer = true;
            } else {
                completed();
                return;
            }
        }

        elapsedTime += delta;
        overlayUIController.updateCastingBar(title, elapsedTime / totalTime);

        if(elapsedTime >= totalTime) completed();
    }

    private boolean isMainPlayer(Entity entity) {
        return km.has(entity);
    }

    @Override
    public void reset() {
        elapsedTime = 0f;
        foundOutIfMainPlayer = false;
    }
}
