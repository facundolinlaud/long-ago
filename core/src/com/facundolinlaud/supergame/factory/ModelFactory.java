package com.facundolinlaud.supergame.factory;

import com.badlogic.gdx.Gdx;
import com.facundolinlaud.supergame.model.entity.PlayerModel;
import com.facundolinlaud.supergame.model.skill.SkillsModel;
import com.facundolinlaud.supergame.model.sprite.RawAnimationModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by facundo on 27/7/16.
 */
public class ModelFactory implements Disposable {
    private static final String PLAYER_MODEL_PATH = "model/entities/player.json";
    private static final String ENEMY_MODEL_PATH = "model/entities/enemy.json";
    private static final String SKILLS_MODEL_PATH = "model/entities/skills.json";
    private static final String DEFAULT_ANIMATION_MODEL_PATH = "model/animations/default.json";

    private static Map<String, Object> cache = new HashMap<>();

    public static PlayerModel getPlayerModel(){
        return (PlayerModel) readModel(PLAYER_MODEL_PATH, PlayerModel.class);
    }

    public static PlayerModel getEnemyModel(){
        return (PlayerModel) readModel(ENEMY_MODEL_PATH, PlayerModel.class);
    }

    public static RawAnimationModel getDefaultAnimationModel(){
        return (RawAnimationModel) readModel(DEFAULT_ANIMATION_MODEL_PATH, RawAnimationModel.class);
    }

    public static SkillsModel getAvailableSkillsModel(){
        return (SkillsModel) readModel(SKILLS_MODEL_PATH, SkillsModel.class);
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
