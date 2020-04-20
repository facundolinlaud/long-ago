package com.facundolinlaud.supergame.skills;

import com.facundolinlaud.supergame.behaviortree.SequentialTask;
import com.facundolinlaud.supergame.behaviortree.Task;

import java.util.LinkedList;

public class Skill extends SequentialTask {
    private String name;

    public Skill(String name, LinkedList<Task> children) {
        super(children);
        this.name = name;
    }

    @Override
    public void completed() {
        System.out.println("fin");
        // avisarle al manager de que ya puede castear?
    }
}
