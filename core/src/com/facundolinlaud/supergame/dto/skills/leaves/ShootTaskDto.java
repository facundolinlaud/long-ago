package com.facundolinlaud.supergame.dto.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.skills.leaves.ShootTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class ShootTaskDto extends TaskDto {
    private String texture;
    private float maxTravelDistance;
    private ParticleType particleType;
    private float shootingForce;

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void setMaxTravelDistance(float maxTravelDistance) {
        this.maxTravelDistance = maxTravelDistance;
    }

    public void setParticleType(ParticleType particleType) {
        this.particleType = particleType;
    }

    public void setShootingForce(float shootingForce) {
        this.shootingForce = shootingForce;
    }

    @Override
    public Task build() {
        return new ShootTask(texture, maxTravelDistance, particleType, shootingForce);
    }
}
