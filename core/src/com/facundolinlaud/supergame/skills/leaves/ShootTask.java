package com.facundolinlaud.supergame.skills.leaves;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.behaviortree.stack.Value;
import com.facundolinlaud.supergame.builder.ProjectileBuilder;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.services.ProjectilesService;
import com.facundolinlaud.supergame.skills.SkillBlackboard;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.function.Consumer;

/**
 * Pops: a position-value corresponding to the projectile destination
 * Pushes:
 * * if an agent is hit, then it is pushed and the task completed()
 * * if no agent is hit, then it's a miss, nothing is pushed and the task is failed()
 */
public class ShootTask extends Task<SkillBlackboard> {
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private String texture;
    private float maxTravelDistance;
    private String particleId;
    private float shootingForce;

    private ProjectilesService projectilesService;

    public ShootTask(String texture, float maxTravelDistance, String particleId, float shootingForce) {
        this.texture = texture;
        this.maxTravelDistance = maxTravelDistance;
        this.particleId = particleId;
        this.shootingForce = shootingForce;
    }

    @Override
    protected void onBlackboardAvailable(SkillBlackboard blackboard) {
        this.projectilesService = getBlackboard().getProjectilesService();
    }

    @Override
    public void activate() {
        Entity caster = getBlackboard().getAgent();
        PositionComponent positionComponent = pm.get(caster);
        Vector2 origin = positionComponent.getPosition();
        Vector2 destination = stack.pop().getPosition();

        createProjectile(caster, origin, destination);
    }

    private void createProjectile(Entity caster, Vector2 origin, Vector2 destination) {
        Consumer<Entity> onHit = victim -> {
            Value value = new Value(victim);
            stack.push(value);
            this.completed();
        };

        Runnable onMiss = () -> this.failed();

        ProjectileBuilder projectile = new ProjectileBuilder(caster, maxTravelDistance, origin, onHit, onMiss)
                .to(destination, shootingForce);

        if (texture != null) projectile.withPicture(texture);

        projectilesService.create(projectile, particleId);
    }
}
