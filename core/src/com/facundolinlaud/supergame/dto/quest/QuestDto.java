package com.facundolinlaud.supergame.dto.quest;

import com.facundolinlaud.supergame.quests.composites.SequentialTask;

public class QuestDto {
  private SequentialTask sequentialTask;

    public SequentialTask getSequentialTask() {
        return sequentialTask;
    }

    public void setSequentialTask(SequentialTask sequentialTask) {
        this.sequentialTask = sequentialTask;
    }

    @Override
    public String toString() {
        return "QuestDto{" +
                "sequentialTask=" + sequentialTask +
                '}';
    }
}
