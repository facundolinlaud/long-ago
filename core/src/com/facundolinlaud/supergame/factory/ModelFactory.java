package com.facundolinlaud.supergame.factory;

import com.badlogic.gdx.Gdx;
import com.facundolinlaud.supergame.dto.agent.Agent;
import com.facundolinlaud.supergame.dto.quests.QuestDto;
import com.facundolinlaud.supergame.model.item.Item;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillTreeModel;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;
import com.facundolinlaud.supergame.model.sprite.SpriteModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 27/7/16.
 */
public class ModelFactory implements Disposable {
    private static final String SKILLS_MODEL_PATH = "model/entities/skills.json";
    private static final String ANIMATIONS_MODEL_PATH = "model/entities/animations.json";
    private static final String PARTICLES_MODEL_PATH = "model/particles/particles.json";
    private static final String ITEMS_MODEL_PATH = "model/entities/items.json";
    private static final String AGENTS_MODEL_PATH = "model/entities/agents.json";
    private static final String SPRITES_MODELS_PATH = "model/textures/sprites.json";
    private static final String SKILL_BAR_MODEL_PATH = "model/player/skill_bar.json";
    private static final String SKILL_TREE_MODEL_PATH = "model/player/skill_tree.json";
    private static final String QUESTS_MODELS_DIRECTORY = "model/quests/";

    private static Map<String, Object> cache = new HashMap<>();
    private static ObjectMapper mapper = new ObjectMapper();

    public static Map<String, RawAnimationModel> getAnimationsModels(){
        TypeReference<HashMap<String, RawAnimationModel>> typeRef =
                new TypeReference<HashMap<String, RawAnimationModel>>() {};

        return (Map<String, RawAnimationModel>) readModel(ANIMATIONS_MODEL_PATH, typeRef);
    }

    public static Map<Integer, Skill> getSkillsModel(){
        TypeReference<HashMap<Integer, Skill>> typeRef = new TypeReference<HashMap<Integer, Skill>>() {};
        return (Map<Integer, Skill>) readModel(SKILLS_MODEL_PATH, typeRef);
    }

    public static Map<ParticleType, String> getParticlesModel(){
        TypeReference<HashMap<ParticleType, String>> typeRef = new TypeReference<HashMap<ParticleType, String>>() {};
        return (Map<ParticleType, String>) readModel(PARTICLES_MODEL_PATH, typeRef);
    }

    public static Map<String, SpriteModel> getSpriteModels(){
        TypeReference<HashMap<String, SpriteModel>> typeRef = new TypeReference<HashMap<String, SpriteModel>>() {};
        return (Map<String, SpriteModel>) readModel(SPRITES_MODELS_PATH, typeRef);
    }

    public static Map<Integer, Item> getItemsModel(){
        TypeReference<HashMap<Integer, Item>> typeRef = new TypeReference<HashMap<Integer, Item>>() {};
        return (Map<Integer, Item>) readModel(ITEMS_MODEL_PATH, typeRef);
    }

    public static Map<Integer, Agent> getAgentsModel(){
        TypeReference<HashMap<Integer, Agent>> typeRef = new TypeReference<HashMap<Integer, Agent>>() {};
        return (Map<Integer, Agent>) readModel(AGENTS_MODEL_PATH, typeRef);
    }

    public static Map<Integer, Integer> getSkillBar(){
        TypeReference<HashMap<Integer, Integer>> typeRef = new TypeReference<HashMap<Integer, Integer>>() {};
        return (Map<Integer, Integer>) readModel(SKILL_BAR_MODEL_PATH, typeRef);
    }

    public static SkillTreeModel getSkillTree(){
        TypeReference<SkillTreeModel> typeRef = new TypeReference<SkillTreeModel>() {};
        return (SkillTreeModel) readModel(SKILL_TREE_MODEL_PATH, typeRef);
    }

    public static QuestDto getQuest(String questFile){
        ObjectMapper customMapper = new ObjectMapper().enableDefaultTyping();

        try {
            return customMapper.readValue(Gdx.files.internal(QUESTS_MODELS_DIRECTORY + questFile)
                    .file(), QuestDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Object readModel(String modelPath, TypeReference typeRef){
        if(!cache.containsKey(modelPath)) {
            try {
                Object o = mapper.readValue(Gdx.files.internal(modelPath).file(), typeRef);
                cache.put(modelPath, o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return cache.get(modelPath);
    }

    @Override
    public void dump() {
        cache.clear();
    }
}
