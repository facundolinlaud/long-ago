package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class SkillClickComponent implements Component {
    public Vector2 clickedPosition;

    public SkillClickComponent() { }

    public void registerClick(Vector2 worldPosition){
        clickedPosition = worldPosition;
    }

    public Vector2 getClickedPosition() {
        return clickedPosition;
    }
}
