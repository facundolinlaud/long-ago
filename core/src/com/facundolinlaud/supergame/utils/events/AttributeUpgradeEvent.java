package com.facundolinlaud.supergame.utils.events;

/**
 * Created by facundo on 3/31/16.
 */
public class AttributeUpgradeEvent extends Event {
    private String attribute;

    public AttributeUpgradeEvent(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
