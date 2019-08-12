package com.facundolinlaud.supergame.ui.view.quest.dialog;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class QuestDialog extends Table {
    public QuestDialog(String message, Skin skin) {
        super(skin);
        setBackground(skin.getDrawable("gothic-slot"));
        TypingLabel label = new TypingLabel(message, skin, Themes.Label.REGULAR_15);
        label.setWrap(true);
        add(label);
        pad(10);
    }
}
