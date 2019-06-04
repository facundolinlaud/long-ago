package com.facundolinlaud.supergame.model.ai;

public enum BehaviorType {
    PASSIVE("passive.tree"),
    AGGRESSIVE("aggressive.tree");

    private String treePath;

    BehaviorType(String treePath) {
        this.treePath = treePath;
    }

    public String getTreePath() {
        return treePath;
    }
}
