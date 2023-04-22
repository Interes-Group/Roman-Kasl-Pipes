package sk.stuba.fei.uim.oop.logic;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum Orientation {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    private final int shiftX;
    private final int shiftY;

    Orientation(int shiftX, int shiftY) {
        this.shiftX = shiftX;
        this.shiftY = shiftY;
    }

    public Orientation next() {
        int index = Arrays.asList(values()).indexOf(this);
        if (index == values().length - 1) {
            index = 0;
        }
        else {
            index++;
        }
        return values()[index];
    }
}
