package sk.stuba.fei.uim.oop.logic;

import java.util.ArrayList;
import java.util.Random;

public class RouteCreator {
    private Random rand = new Random();
    private boolean[][] tiles;
    private Position start;
    private Position finish;
    private ArrayList<Position> route;
    
    public ArrayList<Position> createRoute(int boardSize) {
        tiles = new boolean[boardSize][boardSize];
        initStartAndFinish();
        route = new ArrayList<>();
        route.add(start);
        randomizedDFS(start);
        return this.route;
    }

    private void initStartAndFinish() {
        start = new Position(0, rand.nextInt(tiles.length));
        finish = new Position(tiles.length - 1, rand.nextInt(tiles.length));
    }

    private boolean randomizedDFS(Position tile) {
        tiles[tile.getX()][tile.getY()] = true;
        if (tile.equals(finish)) {
            return true;
        }
        Position nextTile = getUnvisitedNeighbour(tile);
        while (nextTile != null) {
            route.add(new Position(nextTile));
            boolean finished = randomizedDFS(nextTile);
            if (finished) {
                return true;
            }            
            nextTile = getUnvisitedNeighbour(tile);
        }
        route.remove(route.size()-1);
        return false;
    }

    private Position getUnvisitedNeighbour(Position tile) {
        ArrayList<Position> unvisitedNeighbours = new ArrayList<>();
        for (Orientation neighbour : Orientation.values()) {
            Position neighbourTile = new Position(tile.getX() + neighbour.getShiftX(), tile.getY() + neighbour.getShiftY());
            if (neighbourTile.getX() < 0 || neighbourTile.getY() < 0 || neighbourTile.getX() >= this.tiles.length || neighbourTile.getY() >= this.tiles[0].length) {
                continue;
            }
            else {
                if (tiles[neighbourTile.getX()][neighbourTile.getY()] == false) {
                    unvisitedNeighbours.add(neighbourTile);
                }
            }
        }
        if (unvisitedNeighbours.size() > 0) {
            int ind = rand.nextInt(unvisitedNeighbours.size());
            return unvisitedNeighbours.get(ind);
        }
        return null;
    }
}


