package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class SkillClickComponent implements Component {
    public Vector2 clickedPosition;

    public SkillClickComponent() { }

    public void registerClick(){
        clickedPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
    }

    public Vector2 getClickedPosition() {
        return clickedPosition;
    }
}
