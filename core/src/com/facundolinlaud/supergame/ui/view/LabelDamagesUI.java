package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.facundolinlaud.supergame.ui.view.labeldamages.DamageLabel;
import com.facundolinlaud.supergame.utils.Position;

import java.util.Random;


public class LabelDamagesUI {
    private static final float FLOATING_CEIL = 100f;
    private static final int DURATION = 2;

    private static float MIN_X_OFFSET = -16.0f;
    private static float MAX_X_OFFSET = 16.0f;

    private Random random;
    private Stage stage;
    private Skin skin;

    public LabelDamagesUI(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;
        this.random = new Random();
    }

    public void registerNewDamageLabel(Position position, int damage) {
        DamageLabel label = new DamageLabel(String.valueOf(damage), this.skin, position);

        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setPosition(position.x + randomOffset(), position.y + FLOATING_CEIL);
        moveToAction.setDuration(DURATION);

        AlphaAction alphaAction = new AlphaAction();
        alphaAction.setAlpha(0f);
        alphaAction.setInterpolation(Interpolation.pow5In);
        alphaAction.setDuration(DURATION);

        RemoveActorAction removeActorAction = new RemoveActorAction();

        ParallelAction parallelAction = new ParallelAction(moveToAction, alphaAction);
        SequenceAction sequenceAction = new SequenceAction(parallelAction, removeActorAction);

        label.addAction(sequenceAction);

        stage.addActor(label);
    }

    private float randomOffset() {
        return random.nextFloat() * (MAX_X_OFFSET - MIN_X_OFFSET) + MIN_X_OFFSET;
    }
}
