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

    protected Table table;
    protected Label titleLabel;
    protected TypingLabel messageLabel;

    public BaseDialog(Skin skin) {
        setupTable(skin);
        setupTitle(skin);
        setupMessage(skin);
        layoutWidgets();
    }

    public void showDialog(String title, String message){
        titleLabel.setText(title);
        messageLabel.setText(message);
        messageLabel.restart();

        table.setVisible(true);

        messageLabel.pack();
        table.pack();
    }

    private void setupTable(Skin skin){
        table = new Table(skin);
        table.align(Align.topLeft);
        table.setPosition((Gdx.graphics.getWidth() - WIDTH) / 2, 100);
        table.setBackground(skin.getDrawable(Themes.Background.SLOT));
        table.setVisible(false);
        table.pad(PAD);
    }

    private void setupTitle(Skin skin){
        titleLabel = new Label("Title", skin, Themes.Label.SMALL_CAPS_20);
        titleLabel.setColor(Color.ROYAL);
    }

    private void setupMessage(Skin skin){
        messageLabel = new TypingLabel("Message", skin, Themes.Label.SMALL_CAPS_14);
        messageLabel.setColor(Themes.Colors.GRAY);
        messageLabel.setWrap(true);
    }

    private void layoutWidgets() {
        table.add(titleLabel).left();
        table.row();
        table.add(messageLabel).width(WIDTH - PAD).left();
    }

    protected void hideDialog(){
        table.setVisible(false);
    }

    public Actor get() {
        return table;
    }
}
