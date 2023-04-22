package sk.stuba.fei.uim.oop.logic;

import java.util.ArrayList;
import java.util.Random;

import lombok.Getter;

@Getter
public class BoardData {
    private ArrayList<PipeData> route = new ArrayList<>();
    private int size;

    public BoardData(int boardSize) {
        this.size = boardSize;
        RouteCreator routeCreator = new RouteCreator();
        ArrayList<Position> routePos = routeCreator.createRoute(boardSize);
        for (Position position : routePos) {
            this.route.add(new PipeData(position));
        }
        setPipeSides();
        shufflePipeEnds();
    }

    public void rotatePipe(Position pos) {
        for (PipeData pipe : route) {
            if (pipe.getPosition().equals(pos)) {
                pipe.rotate();
            }
        }
    }

    private void setPipeSides() {
        route.get(0).addEnd(Orientation.WEST);
        for (int i = 1; i < route.size(); i++) {
            Orientation previousPipeEnd;
            if (route.get(i-1).getPosition().getX() == route.get(i).getPosition().getX()) {
                if (route.get(i-1).getPosition().getY() < route.get(i).getPosition().getY()) {
                    previousPipeEnd = Orientation.SOUTH;
                }
                else {
                    previousPipeEnd = Orientation.NORTH;
                }
            }
            else {
                if (route.get(i-1).getPosition().getX() < route.get(i).getPosition().getX()) {
                    previousPipeEnd = Orientation.EAST;
                }
                else {
                    previousPipeEnd = Orientation.WEST;
                }
            }
            route.get(i-1).addEnd(previousPipeEnd);
            route.get(i).addEnd(previousPipeEnd.next().next());
        }
        route.get(route.size()-1).addEnd(Orientation.EAST);
    }

    private void shufflePipeEnds() {
        Random rand = new Random();
        for (PipeData pipe : route) {
            int reps = rand.nextInt(4);
            for (int i = 0; i < reps; i++) {
                pipe.rotate();
            }
        }
    }
}
