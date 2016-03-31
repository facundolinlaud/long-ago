package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import com.facundolinlaud.supergame.utils.events.AttributeUpgradeEvent;
import com.facundolinlaud.supergame.utils.mediator.Mediator;
import com.facundolinlaud.supergame.utils.mediator.Messenger;

/**
 * Created by facundo on 3/31/16.
 */
public class AddValue extends TextButton implements Messenger {

    public static final String ADD_VALUE_TEXT = "+";
    public static final int HEIGHT = 32;
    public static final int WIDTH = 32;

    private Mediator uiMediator;
    private String attributeName;

    public AddValue(Mediator uiMediator, String attributeName, Skin skin) {
        super(ADD_VALUE_TEXT, skin, Themes.DEFAULT.toString());
        setSize(WIDTH, HEIGHT);

        this.uiMediator = uiMediator;
        this.attributeName = attributeName;

        addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                uiMediator.raise(AddValue.this, AttributeUpgradeEvent.class, new AttributeUpgradeEvent(attributeName));
            }
        });
    }

}
