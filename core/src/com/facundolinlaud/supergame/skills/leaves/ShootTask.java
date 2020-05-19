package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.builder.ProjectileBuilder;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.services.ProjectilesService;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.Stack;

public class ShootTask extends Task<SkillBlackboard> {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private String texture;
    private float maxTravelDistance;
    private ParticleType particleType;
    private float shootingForce;

    private Stack<Value> stack;
    private ProjectilesService projectilesService;

    public ShootTask(String texture, float maxTravelDistance, ParticleType particleType, float shootingForce) {
        this.texture = texture;
        this.maxTravelDistance = maxTravelDistance;
        this.particleType = particleType;
        this.shootingForce = shootingForce;
    }

    @Override
    public void activate() {
        float y = stack.pop().getFloatValue();
        float x = stack.pop().getFloatValue();

        Entity caster = getBlackboard().getCaster();
        PositionComponent positionComponent = pm.get(caster);
        Vector2 origin = positionComponent.getPosition();
        Vector2 destination = new Vector2(x, y);

        createProjectile(caster, origin, destination);
        completed();
    }

    @Override
    protected void onBlackboardAvailable(SkillBlackboard blackboard) {
        this.projectilesService = getBlackboard().getProjectilesService();
        this.stack = getBlackboard().getStack();
    }

    private void createProjectile(Entity caster, Vector2 origin, Vector2 destination) {
        ProjectileBuilder projectile = new ProjectileBuilder(caster, maxTravelDistance, origin)
                .to(destination, shootingForce) // 7f
                .withPicture(texture);

        projectilesService.create(projectile, particleType);
    }
}
