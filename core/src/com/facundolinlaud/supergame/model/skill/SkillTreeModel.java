package com.facundolinlaud.supergame.model.skill;

import java.util.List;

public class SkillTreeModel {
    private List<List<Boolean>> dependencyGraph;
    private List<List<Integer>> visualRepresentation;

    public SkillTreeModel() {
    }

    public List<List<Boolean>> getDependencyGraph() {
        return dependencyGraph;
    }

    public void setDependencyGraph(List<List<Boolean>> dependencyGraph) {
        this.dependencyGraph = dependencyGraph;
    }

    public List<List<Integer>> getVisualRepresentation() {
        return visualRepresentation;
    }

    public void setVisualRepresentation(List<List<Integer>> visualRepresentation) {
        this.visualRepresentation = visualRepresentation;
    }
}
