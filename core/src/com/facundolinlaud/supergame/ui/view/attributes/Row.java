package com.facundolinlaud.supergame.ui.view.attributes;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by facundo on 3/31/16.
 */
public class Row extends Table {

    private ValueStack valueStack;

    public Row(Skin skin, String name, int value) {
        super(skin);

        this.valueStack = new ValueStack(skin, value);

        add(new Name(skin, name)).width(90).left();
        add(valueStack).expandX().right();
        add(new AddValue(name, skin)).expandX().fill().right().pad(8);
    }

    public void setAttributeValue(int value){
        valueStack.setAttributeValue(value);
    }
}
