package com.facundolinlaud.supergame.model.ai;

public enum BehaviourType {
    PASSIVE("passive.tree"),
    AGGRESSIVE("aggressive.tree");

    private String treePath;

    BehaviourType(String treePath) {
        this.treePath = treePath;
    }

    public String getTreePath() {
        return treePath;
    }
}
