package com.facundolinlaud.supergame.factory;

import com.badlogic.gdx.Gdx;
import com.facundolinlaud.supergame.model.agent.Agent;
import com.facundolinlaud.supergame.model.agent.Agents;
import com.facundolinlaud.supergame.model.ai.EnemyModel;
import com.facundolinlaud.supergame.model.entity.PlayerModel;
import com.facundolinlaud.supergame.model.item.Item;
import com.facundolinlaud.supergame.model.item.Items;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.model.skill.SkillsModel;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by facundo on 27/7/16.
 */
public class ModelFactory implements Disposable {
    private static final String PLAYER_MODEL_PATH = "model/entities/player.json";
    private static final String SKILLS_MODEL_PATH = "model/entities/skills.json";
    private static final String DEFAULT_ANIMATION_MODEL_PATH = "model/animations/default.json";
    private static final String PARTICLES_MODEL_PATH = "model/particles/particles.json";
    private static final String ITEMS_MODEL_PATH = "model/entities/items.json";
    private static final String TEXTURES_PATH = "model/textures/textures.json";
    private static final String AGENTS_MODEL_PATH = "model/entities/agents.json";

    private static Map<String, Object> cache = new HashMap<>();

    public static PlayerModel getPlayerModel(){
        return (PlayerModel) readModel(PLAYER_MODEL_PATH, PlayerModel.class);
    }


    public static RawAnimationModel getDefaultAnimationModel(){
        return (RawAnimationModel) readModel(DEFAULT_ANIMATION_MODEL_PATH, RawAnimationModel.class);
    }

    public static SkillsModel getAvailableSkillsModel(){
        return (SkillsModel) readModel(SKILLS_MODEL_PATH, SkillsModel.class);
    }

    public static Map<ParticleType, String> getParticlesModel(){
        return (Map<ParticleType, String>) readModel(PARTICLES_MODEL_PATH, Map.class);
    }

    public static List<String> getTexturesPaths(){
        return (List<String>) readModel(TEXTURES_PATH, List.class);
    }

    public static Map<Integer, Item> getItemsModel(){
        Items items = (Items) readModel(ITEMS_MODEL_PATH, Items.class);
        return items.getItems();
    }

    public static Map<Integer, Agent> getAgentsModel(){
        Agents agents = (Agents) readModel(AGENTS_MODEL_PATH, Agents.class);
        return agents.getAgents();
    }

    private static Object readModel(String modelPath, Class clazz){
        if(cache.containsKey(modelPath)){
            return cache.get(modelPath);
        }else {
            ObjectMapper mapper = new ObjectMapper();

            try {
                Object o = mapper.readValue(Gdx.files.internal(modelPath).file(), clazz);
                cache.put(modelPath, o);
                return o;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    public void dump() {
        cache.clear();
    }
}
