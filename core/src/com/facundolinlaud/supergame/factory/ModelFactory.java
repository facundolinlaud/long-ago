package com.facundolinlaud.supergame.factory;

import com.badlogic.gdx.Gdx;
import com.facundolinlaud.supergame.dto.agent.Agent;
import com.facundolinlaud.supergame.dto.agent.Agents;
import com.facundolinlaud.supergame.model.item.Item;
import com.facundolinlaud.supergame.model.item.Items;
import com.facundolinlaud.supergame.model.particle.ParticleType;
import com.facundolinlaud.supergame.model.particle.Particles;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillBarModel;
import com.facundolinlaud.supergame.model.skill.SkillTreeModel;
import com.facundolinlaud.supergame.model.skill.SkillsModel;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;
import com.facundolinlaud.supergame.model.sprite.SpriteModel;
import com.facundolinlaud.supergame.model.sprite.SpritesModels;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 27/7/16.
 */
public class ModelFactory implements Disposable {
    private static final String SKILLS_MODEL_PATH = "model/entities/skills.json";
    private static final String DEFAULT_ANIMATION_MODEL_PATH = "model/animations/default.json";
    private static final String PARTICLES_MODEL_PATH = "model/particles/particles.json";
    private static final String ITEMS_MODEL_PATH = "model/entities/items.json";
    private static final String AGENTS_MODEL_PATH = "model/entities/agents.json";
    private static final String SPRITES_MODELS_PATH = "model/textures/sprites.json";
    private static final String SKILL_BAR_MODEL_PATH = "model/player/skill_bar.json";
    private static final String SKILL_TREE_MODEL_PATH = "model/player/skill_tree.json";

    private static Map<String, Object> cache = new HashMap<>();

    public static RawAnimationModel getDefaultAnimationModel(){
        return (RawAnimationModel) readModel(DEFAULT_ANIMATION_MODEL_PATH, RawAnimationModel.class);
    }

    public static Map<Integer, Skill> getSkillsModel(){
        SkillsModel skillsModel = (SkillsModel) readModel(SKILLS_MODEL_PATH, SkillsModel.class);
        return skillsModel.getSkills();
    }

    public static Map<ParticleType, String> getParticlesModel(){
        Particles particles = (Particles) readModel(PARTICLES_MODEL_PATH, Particles.class);
        return particles.getParticles();
    }

    public static Map<String, SpriteModel> getSpriteModels(){
        SpritesModels models = (SpritesModels) readModel(SPRITES_MODELS_PATH, SpritesModels.class);
        return models.getSprites();
    }

    public static Map<Integer, Item> getItemsModel(){
        Items items = (Items) readModel(ITEMS_MODEL_PATH, Items.class);
        return items.getItems();
    }

    public static Map<Integer, Agent> getAgentsModel(){
        Agents agents = (Agents) readModel(AGENTS_MODEL_PATH, Agents.class);
        return agents.getAgents();
    }

    public static Map<Integer, Integer> getSkillBar(){
        SkillBarModel skillBarModel = (SkillBarModel) readModel(SKILL_BAR_MODEL_PATH, SkillBarModel.class);
        return skillBarModel.getButtonsToSkillsIds();
    }

    public static SkillTreeModel getSkillTree(){
        SkillTreeModel skillTree = (SkillTreeModel) readModel(SKILL_TREE_MODEL_PATH, SkillTreeModel.class);
        return skillTree;
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
