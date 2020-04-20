package com.facundolinlaud.supergame.dto.quests;

import com.facundolinlaud.supergame.dto.behaviortree.QuestListDto;
import com.facundolinlaud.supergame.dto.behaviortree.SequentialTaskDto;
import com.facundolinlaud.supergame.quests.Quest;

public class QuestDto extends SequentialTaskDto {
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
    public Quest build() {
        return new Quest(name, buildChildren());
    }
}
