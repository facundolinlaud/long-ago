package com.facundolinlaud.supergame.components.sprite;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.facundolinlaud.supergame.model.status.Status;

import java.util.*;

/**
 * Created by facundo on 26/7/16.
 */
public class AnimableSpriteComponent implements Component {
    private List<Map<Status, Animation>> texturesToAnimations;
    public float stateTime = 0f;

    public AnimableSpriteComponent() {
        this.texturesToAnimations = new ArrayList();
    }

    public List<Map<Status, Animation>> getTexturesToAnimations() {
        return texturesToAnimations;
    }

    public void setTexturesToAnimations(List<Map<Status, Animation>> texturesToAnimations) {
        this.texturesToAnimations = texturesToAnimations;
    }
}
