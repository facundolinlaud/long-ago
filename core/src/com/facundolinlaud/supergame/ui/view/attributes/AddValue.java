package com.facundolinlaud.supergame.ui.view.attributes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import com.facundolinlaud.supergame.utils.events.AttributeUpgradeEvent;
import com.facundolinlaud.supergame.utils.events.Messages;

/**
 * Created by facundo on 3/31/16.
 */
public class AddValue extends TextButton {
    public static final String ADD_VALUE_TEXT = "+";

    public AddValue(String attributeName, Skin skin) {
        super(ADD_VALUE_TEXT, skin, Themes.TextButton.DEFAULT);

        addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MessageManager.getInstance().dispatchMessage(Messages.ATTRIBUTE_UPGRADED,
                        new AttributeUpgradeEvent(attributeName));
            }
        });
    }

}
