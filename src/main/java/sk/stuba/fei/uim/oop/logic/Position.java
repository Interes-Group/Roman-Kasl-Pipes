package sk.stuba.fei.uim.oop.logic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    private int x = 0;
    private int y = 0;

    public Position() {}

    public Position(Position origin) {
        this.x = origin.getX();
        this.y = origin.getY();
    }
}
