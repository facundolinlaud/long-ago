package com.facundolinlaud.supergame.ui.view.attributes;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.model.Attributes;
import com.facundolinlaud.supergame.ui.view.cross.GothicWindow;

/**
 * Created by facundo on 3/31/16.
 */
public class AttributesWindow extends GothicWindow {
    private static final String TITLE = "Attributes";

    private AttributesGrid attributesGrid;

    public AttributesWindow(Skin skin) {
        super(TITLE, skin);
        this.setVisible(false);
        this.attributesGrid = new AttributesGrid(skin);

        setSize(250, 270);
        padLeft(20);
        padRight(20);

        add(this.attributesGrid).expandX().fillX();
    }

    public void update(Attributes attributes){
        if(isVisible()) attributesGrid.update(attributes);
    }
}
