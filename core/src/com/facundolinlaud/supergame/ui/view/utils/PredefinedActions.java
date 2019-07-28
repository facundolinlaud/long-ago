package com.facundolinlaud.supergame.ui.view.utils;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.Random;

public class PredefinedActions {
    private static final float FLOATING_CEIL = 100f;
    private static final int DURATION = 2;

    private static Random random = new Random();

    public static void addFloatingLabelActions(Label label, float minOffsetX, float maxOffsetX){
        MoveToAction moveToAction = new MoveToAction();
        moveToAction.setPosition(label.getX() + randomOffset(minOffsetX, maxOffsetX),
                label.getY() + FLOATING_CEIL);
        moveToAction.setDuration(DURATION);

        AlphaAction alphaAction = new AlphaAction();
        alphaAction.setAlpha(0f);
        alphaAction.setInterpolation(Interpolation.pow5In);
        alphaAction.setDuration(DURATION);

        RemoveActorAction removeActorAction = new RemoveActorAction();

        ParallelAction parallelAction = new ParallelAction(moveToAction, alphaAction);
        SequenceAction sequenceAction = new SequenceAction(parallelAction, removeActorAction);

        label.addAction(sequenceAction);
    }

    private static float randomOffset(float minOffsetX, float maxOffsetY) {
        return random.nextFloat() * (maxOffsetY - minOffsetX) + minOffsetX;
    }
}
