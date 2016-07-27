package com.facundolinlaud.supergame.model;

/**
 * Created by facundo on 3/19/16.
 */
public class RenderPriority {
    private static final int MAX = 10;
    private static final int MIN = 0;

    private int priority;

    public RenderPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if(priority < MIN)
            priority = MIN;
        else if(priority > MAX)
            priority = MAX;

        this.priority = priority;
    }

    public static RenderPriority createNormalRenderPriority(){
        return new RenderPriority(5);
    }
}
