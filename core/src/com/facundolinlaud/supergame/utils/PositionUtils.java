package com.facundolinlaud.supergame.utils;

import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.model.status.Direction;

public class PositionUtils {
    public static Vector2 getCell(Vector2 position) {
        return new Vector2((int) position.x, (int) position.y);
    }

    public static Direction getCellFacingDirection(Vector2 fromCell, Vector2 toCell) {
        Vector2 offset = new Vector2(getCell(fromCell)).sub(toCell);

        if (offset.x < 0) {
            return Direction.RIGHT;
        } else if (offset.x > 0) {
            return Direction.LEFT;
        } else if (offset.y < 0) {
            return Direction.UP;
        }

        return Direction.DOWN;
    }

    public static Direction getFacingDirection(Vector2 from, Vector2 to) {
        float offsetX = to.x - from.x;
        float offsetY = to.y - from.y;

        if (Math.abs(offsetX) > Math.abs(offsetY)) {
            if (offsetX > 0) {
                return Direction.RIGHT;
            } else {
                return Direction.LEFT;
            }
        } else {
            if (offsetY > 0) {
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        }
    }

    /**
     * Gets the main player facing direction in relation to the cursor position
     *
     * @param cursorPosition
     * @return
     */
    public static Direction getFacingDirection(Vector2 cursorPosition) {
        if (Math.abs(cursorPosition.x) > Math.abs(cursorPosition.y)) {
            if (cursorPosition.x > 0)
                return Direction.RIGHT;
            else
                return Direction.LEFT;
        } else {
            if (cursorPosition.y > 0)
                return Direction.DOWN;
            else
                return Direction.UP;
        }
    }
}
