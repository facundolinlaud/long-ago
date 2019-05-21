package com.facundolinlaud.supergame.components.skills;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class SkillClickComponent implements Component {
    private Vector2 clickedPosition;
    private boolean justCreated;
    private boolean hasClicked = false;

    public SkillClickComponent() {
        this.justCreated = true;
    }

    public SkillClickComponent(Vector2 epicenter){
        this.clickedPosition = epicenter;
        this.justCreated = false;
        this.hasClicked = true;
    }

    public void registerClick(Vector2 worldPosition){
        clickedPosition = worldPosition;
        hasClicked = true;
    }

    public Vector2 getClickedPosition() {
        return clickedPosition;
    }

    public boolean isJustCreated() {
        return justCreated;
    }

    public void setJustCreated(boolean justCreated) {
        this.justCreated = justCreated;
    }

    public boolean isRegistered(){
        return clickedPosition != null;
    }
}
