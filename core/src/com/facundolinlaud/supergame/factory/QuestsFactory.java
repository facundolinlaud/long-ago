package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.dto.quests.QuestListDto;
import com.facundolinlaud.supergame.dto.quests.QuestTaskDto;
import com.facundolinlaud.supergame.quests.QuestTask;
import com.facundolinlaud.supergame.quests.QuestBlackboard;

import java.util.HashMap;
import java.util.Map;

public class QuestsFactory {
    private static final String INITIAL_QUEST = "start.json";

    private Map<String, QuestTask> quests;

    public QuestsFactory() {
        quests = new HashMap();
    }

    public QuestTask buildQuestChain(QuestBlackboard blackboard) {
        QuestTaskDto dto = ModelFactory.getQuest(INITIAL_QUEST);
        processQuestModel(INITIAL_QUEST, dto);
        withBlackboard(blackboard);

        return quests.get(INITIAL_QUEST);
    }

    private QuestTask processQuestModel(String questId, QuestTaskDto dto) {
        QuestTask quest = buildQuest(dto);
        quests.put(questId, quest);

        QuestListDto nextQuests = dto.getNextQuests();

        for (String nextId : nextQuests) {
            QuestTaskDto nextQuestDto = ModelFactory.getQuest(nextId);
            QuestTask nextQuest = processQuestModel(nextId, nextQuestDto);
            quest.addNextQuest(nextQuest);
        }

        return quest;
    }

    private QuestTask buildQuest(QuestTaskDto dto) {
        return dto.build();
    }

    private void withBlackboard(QuestBlackboard blackboard) {
        quests.values().forEach(q -> q.setBlackboard(blackboard));
    }
}
