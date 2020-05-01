package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.services.ParticlesService;
import com.facundolinlaud.supergame.skills.SkillBlackboard;

/**
 * Pops: two float-values corresponding to the x and y particles position values respectively
 * Pushes: nothing
 */
public class DisplayParticlesTask extends Task<SkillBlackboard> {
    private ParticleType particleType;

    public DisplayParticlesTask(ParticleType particleType) {
        this.particleType = particleType;
    }

    @Override
    public void activate() {
        System.out.println("Activating DisplayParticles");

        float y = getBlackboard().popFloat();
        float x = getBlackboard().popFloat();
        Vector2 position = new Vector2(x, y);

        ParticlesService particlesService = getBlackboard().getParticlesService();
        particlesService.create(particleType, position);

        completed();
    }

    @Override
    public void completed() {
        System.out.println("Completing DisplayParticles");
        super.completed();
    }
}
