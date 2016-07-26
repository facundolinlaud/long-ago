package com.facundolinlaud.supergame.refactor.factory;

import com.badlogic.gdx.Gdx;
import com.facundolinlaud.supergame.refactor.model.AnimationModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by facundo on 7/5/16.
 */
public class AnimationFactory {

    public static final String DEFAULT_ANIMATION = "model/animations/default.json";

    public static AnimationModel getDefaultModel(){
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(Gdx.files.internal(DEFAULT_ANIMATION).file(), AnimationModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
