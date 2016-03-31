package com.facundolinlaud.supergame.ui.view.attributes;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;

/**
 * Created by facundo on 3/31/16.
 */
public class ValueStack extends Stack {

    public static final int HEIGHT = 32;
    public static final int WIDTH = 32;

    private ValueLabel valueLabel;

    public ValueStack(Skin skin, int value) {
        setSize(WIDTH, HEIGHT);

        this.valueLabel = new ValueLabel(value, skin);

        add(new ValueSlot(skin));
        add(valueLabel);
    }

    public void setAttributeValue(int value){
        this.valueLabel.setAttributeValue(value);
    }
}
