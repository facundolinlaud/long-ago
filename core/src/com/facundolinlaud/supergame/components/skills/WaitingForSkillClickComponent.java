package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class WaitingForSkillClickComponent implements Component {
    public boolean waiting;
    public Vector2 clickedPosition;

    public WaitingForSkillClickComponent() {
        this.waiting = true;
    }

    public void registerClick(){
        clickedPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        waiting = false;
    }
}
