package com.facundolinlaud.supergame.ui.view.attributes;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.model.Attributes;
import com.facundolinlaud.supergame.ui.view.cross.GothicWindow;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

/**
 * Created by facundo on 3/31/16.
 */
public class AttributesWindow extends GothicWindow {
    private static final String TITLE = "Attributes";

    private AttributesGrid attributesGrid;

    public AttributesWindow(Skin skin) {
        super(TITLE, skin, Themes.Background.DARK);
        this.setVisible(false);
        this.attributesGrid = new AttributesGrid(skin);

        setSize(240, 270);
        padLeft(20);
        padRight(10);

        add(this.attributesGrid).expandX().fillX();
    }

    public void update(Attributes attributes){
        if(isVisible()) attributesGrid.update(attributes);
    }
}
