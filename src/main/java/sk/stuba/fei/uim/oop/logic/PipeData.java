package sk.stuba.fei.uim.oop.logic;

import java.util.Arrays;

import lombok.Getter;

public class PipeData {
    private Orientation[] currEnds  = new Orientation[2];
    private Orientation[] correctEnds  = new Orientation[2];
    @Getter
    Position position;

    public PipeData(Position position) {
        this.position = position;
    }

    public void addEnd(Orientation end) {
        if (correctEnds[0] == null) {
            correctEnds[0] = end;
            currEnds[0] = end;
        }
        else if (this.correctEnds[1] == null) {
            correctEnds[1] = end;
            currEnds[1] = end;
        }
    }

    public Orientation[] getPipeEnds() {
        return this.currEnds;
    }

    public void rotate() {
        this.currEnds[0] = this.currEnds[0].next();
        this.currEnds[1] = this.currEnds[1].next();
    }

    public boolean isCorrect() {
        return Arrays.equals(currEnds, correctEnds) || (currEnds[0] == correctEnds[1] && currEnds[1] == correctEnds[0]);
    }
} 
