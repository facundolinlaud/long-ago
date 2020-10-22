package com.facundolinlaud.supergame.behaviortree.leaves;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.LeafTask;
import com.facundolinlaud.supergame.behaviortree.composites.Blackboard;
import com.facundolinlaud.supergame.services.ParticlesService;

/**
 * Pops: a position-value corresponding to the particles position
 * Pushes: nothing
 */
public class DisplayParticlesTask extends LeafTask<Blackboard> {
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
        Vector2 position = stack.pop().getPosition();

        particlesService.create(particleId, position);
        completed();
    }
}
