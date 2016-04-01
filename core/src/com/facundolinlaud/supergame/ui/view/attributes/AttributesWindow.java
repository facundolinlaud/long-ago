package com.facundolinlaud.supergame.ui.view.attributes;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.facundolinlaud.supergame.ui.model.Attributes;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import com.facundolinlaud.supergame.utils.mediator.Mediator;

/**
 * Created by facundo on 3/31/16.
 */
public class AttributesWindow extends Window {
    private static final String TITLE = "Attributes";

    private Grid grid;

    public AttributesWindow(Mediator uiMediator, Skin skin) {
        super(TITLE, skin, Themes.CLASSIC.toString());

        setVisible(false);
        this.grid = new Grid(uiMediator, skin);
        add(this.grid).expandX().fillX();
        setSize(270, 300);
        adjustTitlePosition();
    }

    private void adjustTitlePosition() {
        getTitleTable().center().top().padLeft(30);
    }

    public void update(Attributes attributes){
        if(isVisible()) grid.update(attributes);
    }
}
