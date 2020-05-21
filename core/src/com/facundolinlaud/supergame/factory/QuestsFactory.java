package com.facundolinlaud.supergame.factory;

import com.facundolinlaud.supergame.dto.behaviortree.QuestListDto;
import com.facundolinlaud.supergame.dto.quests.QuestDto;
import com.facundolinlaud.supergame.quests.Quest;
import com.facundolinlaud.supergame.quests.QuestBlackboard;

import java.util.HashMap;
import java.util.Map;

public class QuestsFactory {
    private static final String INITIAL_QUEST = "start.json";

    private Map<String, Quest> quests;

    public QuestsFactory() {
        quests = new HashMap();
    }

    public Quest buildQuestChain(QuestBlackboard blackboard) {
        QuestDto dto = ModelFactory.getQuest(INITIAL_QUEST);
        processQuestModel(INITIAL_QUEST, dto);
        withBlackboard(blackboard);

        return quests.get(INITIAL_QUEST);
    }

    private Quest processQuestModel(String questId, QuestDto dto) {
        Quest quest = buildQuest(dto);
        quests.put(questId, quest);

        QuestListDto nextQuests = dto.getNextQuests();

        for (String nextId : nextQuests) {
            QuestDto nextQuestDto = ModelFactory.getQuest(nextId);
            Quest nextQuest = processQuestModel(nextId, nextQuestDto);
            quest.addNextQuest(nextQuest);
        }

        return quest;
    }

    private Quest buildQuest(QuestDto dto) {
        return dto.build();
    }

    private void withBlackboard(QuestBlackboard blackboard) {
        quests.values().forEach(q -> q.setBlackboard(blackboard));
    }
}
