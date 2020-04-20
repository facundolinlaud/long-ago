package com.facundolinlaud.supergame.dto.skills.leaves;

import com.facundolinlaud.supergame.behaviortree.Task;
import com.facundolinlaud.supergame.dto.behaviortree.TaskDto;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.skills.leaves.DisplayParticlesTask;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class DisplayParticlesTaskDto extends TaskDto {
    private ParticleType particleType;

    public void setParticleType(ParticleType particleType) {
        this.particleType = particleType;
    }

    @Override
    public Task build() {
        return new DisplayParticlesTask(particleType);
    }
}
