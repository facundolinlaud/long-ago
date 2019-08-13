package com.facundolinlaud.supergame.ui.view.quest.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;

import static com.facundolinlaud.supergame.utils.events.Messages.QUEST_DIALOG_CONTINUED;

public class QuestDialog {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 120;
    private static final int PAD = 10;

    private Table table;
    private Label titleLabel;
    private TypingLabel messageLabel;
    private MessageDispatcher messageDispatcher;

    public QuestDialog(Skin skin) {
        messageDispatcher = MessageManager.getInstance();

        setupTable(skin);
        setupTitle(skin);
        setupDialog(skin);

        table.align(Align.topLeft);
        table.add(titleLabel).fillX().left();
        table.row().padBottom(3 * PAD);
        table.add(messageLabel).width(WIDTH - PAD).left();
    }

    public void showDialog(String title, String message){
        titleLabel.setText(title);
        messageLabel.setText(message);
        table.setVisible(true);
    }

    private void hideDialog(){
        table.setVisible(false);
    }

    private void setupTable(Skin skin){
        table = new Table(skin);
        table.setBackground(skin.getDrawable("gothic-slot"));
        table.setVisible(false);
        table.setPosition((Gdx.graphics.getWidth() - WIDTH) / 2, 100);
        table.setSize(WIDTH, HEIGHT);
        table.pad(PAD);
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

    public void onContinueKeyPressed() {
        if(!messageLabel.hasEnded()){
            messageLabel.skipToTheEnd();
        }else{
            hideDialog();
            messageDispatcher.dispatchMessage(QUEST_DIALOG_CONTINUED);
        }
    }

    public Actor get() {
        return table;
    }
}
