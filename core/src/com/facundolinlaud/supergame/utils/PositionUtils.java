package com.facundolinlaud.supergame.utils;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.model.status.Direction;

public class PositionUtils {
    public static Direction getFacingDirection(Vector2 agentPosition, Vector2 playerPosition) {
        float offsetX = playerPosition.x - agentPosition.x;
        float offsetY = playerPosition.y - agentPosition.y;

        if(Math.abs(offsetX) > Math.abs(offsetY)){
            if(offsetX > 0){
                return Direction.RIGHT;
            }else{
                return Direction.LEFT;
            }
        }else{
            if(offsetY > 0){
                return Direction.UP;
            }else{
                return Direction.DOWN;
            }
        }
    }
}
