package com.facundolinlaud.supergame.managers.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.facundolinlaud.supergame.model.Direction;

import java.util.Stack;

public class PlayerInputObserver extends InputListener {
    private Stack<Direction> movementKeysPressed;

    public PlayerInputObserver() {
        movementKeysPressed = new Stack<>();
    }

    public boolean isGatheringRequeste(){
        return Gdx.input.isKeyJustPressed(Input.Keys.E);
    }

    public boolean isAttackingRequested(){
        return Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    public Direction getPlayersNewDirection(){
        int keysPressed = movementKeysPressed.size();

        if(keysPressed == 0)
            return null;
        else
            return movementKeysPressed.peek();
    }

    private Direction keycodeToDirection(int keycode){
        switch(keycode){
            case Input.Keys.W:
                return Direction.UP;
            case Input.Keys.S:
                return Direction.DOWN;
            case Input.Keys.A:
                return Direction.LEFT;
            case Input.Keys.D:
                return Direction.RIGHT;
        }

        return null;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        Direction direction = keycodeToDirection(keycode);

        if(direction != null && !movementKeysPressed.contains(direction)){
            movementKeysPressed.push(direction);
        }

        return super.keyDown(event, keycode);
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        Direction direction = keycodeToDirection(keycode);

        if(direction != null)
            movementKeysPressed.remove(direction);

        return super.keyUp(event, keycode);
    }
}
