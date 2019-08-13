package com.facundolinlaud.supergame.ui.view.quest.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

public class BaseDialog {
    private static final int WIDTH = 400;
    private static final int PAD = 10;

    protected Table container;
    protected Label titleLabel;
    protected TypingLabel messageLabel;

    public BaseDialog(Skin skin, float height) {
        setupTable(skin, height);
        setupTitle(skin);
        setupDialog(skin);

        container.align(Align.topLeft);
        container.add(titleLabel).fillX().left();
        container.row().padBottom(3 * PAD);
        container.add(messageLabel).width(WIDTH - PAD).left();
    }

    public void showDialog(String title, String message){
        titleLabel.setText(title);
        messageLabel.setText(message);
        messageLabel.restart();
        container.setVisible(true);
    }

    protected void hideDialog(){
        container.setVisible(false);
    }

    private void setupTable(Skin skin, float height){
        container = new Table(skin);
        container.setPosition((Gdx.graphics.getWidth() - WIDTH) / 2, 100);
        container.setBackground(skin.getDrawable(Themes.Background.SLOT));
        container.setSize(WIDTH, height);
        container.align(Align.topLeft);
        container.setVisible(false);
        container.setWidth(WIDTH);
        container.pad(PAD);
    }

    private void setupTitle(Skin skin){
        titleLabel = new Label("Title", skin, Themes.Label.SMALL_CAPS_20);
        titleLabel.setColor(Color.ROYAL);
    }

    private void setupDialog(Skin skin){
        messageLabel = new TypingLabel("Body", skin, Themes.Label.SMALL_CAPS_14);
        messageLabel.setColor(Themes.Colors.GRAY);
        messageLabel.setWrap(true);
    }

    public Actor get() {
        return container;
    }
}
