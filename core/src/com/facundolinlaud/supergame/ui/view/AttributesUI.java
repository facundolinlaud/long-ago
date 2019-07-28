package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.facundolinlaud.supergame.ui.model.Attributes;
import com.facundolinlaud.supergame.ui.view.attributes.AttributesWindow;

/**
 * Created by facundo on 3/31/16.
 */
public class AttributesUI implements UI {

    private AttributesWindow window;

    public AttributesUI(Skin skin) {
        this.window = new AttributesWindow(skin);
    }

    public void update(Attributes attributes){
        this.window.update(attributes);
    }

    @Override
    public Table get() {
        return this.window;
    }
}
