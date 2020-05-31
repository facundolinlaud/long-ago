package com.facundolinlaud.supergame.model.skill;

import java.util.List;

public class SkillTreeModel {
    private List<List<String>> visualRepresentation;

    public SkillTreeModel() {
    }

    public List<List<String>> getVisualRepresentation() {
        return visualRepresentation;
    }

    public void setVisualRepresentation(List<List<String>> visualRepresentation) {
        this.visualRepresentation = visualRepresentation;
    }
}
