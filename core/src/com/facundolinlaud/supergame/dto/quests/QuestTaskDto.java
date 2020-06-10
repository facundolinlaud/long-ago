package com.facundolinlaud.supergame.dto.quests;

import com.facundolinlaud.supergame.dto.behaviortree.composites.KeepTryingTaskDto;
import com.facundolinlaud.supergame.quests.QuestTask;

public class QuestTaskDto extends KeepTryingTaskDto {
    private String name;
    private QuestListDto nextQuests;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public QuestListDto getNextQuests() {
        return nextQuests;
    }

    public void setNextQuests(QuestListDto nextQuests) {
        this.nextQuests = nextQuests;
    }

    @Override
    public QuestTask build() {
        return new QuestTask(name, getChild().build());
    }
}
