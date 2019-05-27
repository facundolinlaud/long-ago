package com.facundolinlaud.supergame.model.particle;

import java.util.Map;

public class Particles {
    private Map<ParticleType, String> particles;

    public Particles() {
    }

    public Map<ParticleType, String> getParticles() {
        return particles;
    }

    public void setParticles(Map<ParticleType, String> particles) {
        this.particles = particles;
    }
}
