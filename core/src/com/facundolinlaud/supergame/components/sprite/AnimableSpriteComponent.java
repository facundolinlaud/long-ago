package com.facundolinlaud.supergame.components.sprite;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.facundolinlaud.supergame.model.status.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 26/7/16.
 */
public class AnimableSpriteComponent implements Component {
    public Map<Status, Animation> animations;
    public float stateTime = 0f;

    public AnimableSpriteComponent() {
        this.animations = new HashMap<>();
    }

    public void resetStateTime(){
        this.stateTime = 0f;
    }
}
