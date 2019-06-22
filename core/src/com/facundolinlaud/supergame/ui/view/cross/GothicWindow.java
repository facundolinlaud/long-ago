package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

public class GothicWindow extends Window {

    public GothicWindow(String title, Skin skin) {
        super(title, skin, Themes.GOTHIC.toString());
        setup();
        adjustTitlePosition();
    }

    private void setup(){
        pad(50, 5, 14, 5);
        align(Align.topLeft);
    }
    private void adjustTitlePosition() {
        getTitleTable().align(Align.top);
        getTitleLabel().setAlignment(Align.center);
    }
}
