package com.facundolinlaud.supergame.managers.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.utils.Dimensions;

import java.util.Stack;

public class PlayerInputObserver extends ClickListener {
    private Stack<Direction> movementKeysPressed;
    private Stack<Integer> skillKeysPressed;
    private Vector2 latestClickedPosition;
    private boolean isClicking;

    public PlayerInputObserver() {
        movementKeysPressed = new Stack<>();
        skillKeysPressed = new Stack<>();
    }

    public boolean isGatheringRequested(){
        return Gdx.input.isKeyJustPressed(Input.Keys.E);
    }

    public boolean isAttackingRequested(){
        return skillKeysPressed.size() > 0;
    }

    public Direction getPlayersNewDirection(){
        int keysPressed = movementKeysPressed.size();

        if(keysPressed == 0)
            return null;
        else
            return movementKeysPressed.peek();
    }

    public Integer getPlayersSkillId(){
        int keysPressed = skillKeysPressed.size();

        if(keysPressed == 0)
            return null;
        else
            return skillKeysPressed.pop();
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

    private Integer keycodeToSkillId(int keycode){
        if(keycode >= 8 && keycode <= 11){
            return new Integer(keycode - 8);
        }

        return null;
    }

    public Vector2 getLatestClickedPositionInMetersRelativeToScreenCenter() {
        return Dimensions.calculateGlobalPositionInPixelsToMetersRelativeToCenter(latestClickedPosition);
    }

    public boolean isClicking() {
        return this.isClicking;
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        Direction direction = keycodeToDirection(keycode);

        if(direction != null && !movementKeysPressed.contains(direction)){
            movementKeysPressed.push(direction);
        }

        Integer skillId = keycodeToSkillId(keycode);

        if(skillId != null && !skillKeysPressed.contains(skillId)){
            skillKeysPressed.push(skillId);
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

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        this.isClicking = true;
        this.latestClickedPosition = new Vector2(x, y);

        return super.touchDown(event, x, y, pointer, button);
    }


    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        this.isClicking = false;

        super.touchUp(event, x, y, pointer, button);
    }
}
