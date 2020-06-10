package com.facundolinlaud.supergame.behaviortree.leaves;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.skills.SkillBlackboard;

/**
 * Pops: two float-values corresponding to the x and y particles position values respectively
 * Pushes: nothing
 */
public class DisplayParticlesTask extends Task<Blackboard> {
    private ParticlesService particlesService;

    private String particleId;

    public DisplayParticlesTask(String particleId) {
        this.particleId = particleId;
    }

    @Override
    protected void onBlackboardAvailable(Blackboard blackboard) {
        particlesService = blackboard.getParticlesService();
    }

    @Override
    public void activate() {
        float y = stack.pop().getFloat();
        float x = stack.pop().getFloat();
        Vector2 position = new Vector2(x, y);

        particlesService.create(particleId, position);
        completed();
    }
}
