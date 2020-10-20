package com.facundolinlaud.supergame.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapGraphCreator {
    public static final int CAPACITY = 4;
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";

    private Set<Point> obstacles;
    private TiledMap tiledMap;

    public MapGraphCreator(TiledMap tiledMap, List<Rectangle> rectangleObstacles) {
        this.tiledMap = tiledMap;
        this.obstacles = new HashSet<>();

        calculateNodeObstacles(rectangleObstacles);
    }

    private void calculateNodeObstacles(List<Rectangle> rectangleObstacles) {
        for(Rectangle rectangle : rectangleObstacles){
            int columns = (int) Math.ceil(rectangle.width);
            int rows = (int) Math.ceil(rectangle.height);

            int x = (int) rectangle.x;
            int y = (int) rectangle.y;

            for(int column = 0; column < columns; column++){
                for(int row = 0; row < rows; row++){
                    Point obstacle = new Point(x + column, y + row);
                    obstacles.add(obstacle);
                }
            }
        }
    }

    public MapGraph createMapGraphFromTiledMap(){
        MapProperties prop = tiledMap.getProperties();

        int mapColumns = prop.get(WIDTH, Integer.class);
        int mapRows = prop.get(HEIGHT, Integer.class);
        int totalAmountOfTiles = mapColumns * mapRows;

        final Node[][] nodes = new Node[mapColumns][mapRows];
        final Array<Node> indexedNodes = new Array<>(totalAmountOfTiles);

        int index = 0;
        for (int y = 0; y < mapRows; y++) {
            for (int x = 0; x < mapColumns; x++, index++) {
                nodes[x][y] = new Node(index, x, y, CAPACITY);
                indexedNodes.add(nodes[x][y]);
            }
        }

        index = 0;
        for (int y = 0; y < mapRows; y++, index++) {
            for (int x = 0; x < mapColumns; x++, index++) {
                if (!isTileWalkable(x, y)) {
                    continue;
                }

                Array<Connection<Node>> connections = nodes[x][y].getConnections();

                if (x - 1 >= 0 && isTileWalkable(x - 1, y)) {
                    connections.add(new DefaultConnection<>(nodes[x][y], nodes[x - 1][y]));
                }


                if (x + 1 < mapColumns && isTileWalkable(x + 1, y)) {
                    connections.add(new DefaultConnection<>(nodes[x][y], nodes[x + 1][y]));
                }


                if (y - 1 >= 0 && isTileWalkable(x, y - 1)) {
                    connections.add(new DefaultConnection<>(nodes[x][y], nodes[x][y - 1]));
                }

                if (y + 1 < mapRows && isTileWalkable(x, y + 1)) {
                    connections.add(new DefaultConnection<>(nodes[x][y], nodes[x][y + 1]));
                }
            }
        }

        return new MapGraph(indexedNodes);
    }

    private boolean isTileWalkable(int x, int y){
        Point point = new Point(x, y);
        return !obstacles.contains(point);
    }
}
