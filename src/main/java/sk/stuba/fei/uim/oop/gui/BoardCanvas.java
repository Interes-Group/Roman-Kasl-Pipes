package sk.stuba.fei.uim.oop.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.listeners.BoardListener;
import sk.stuba.fei.uim.oop.listeners.BoardMotionListener;
import sk.stuba.fei.uim.oop.logic.BoardData;
import sk.stuba.fei.uim.oop.logic.Orientation;
import sk.stuba.fei.uim.oop.logic.PipeData;
import sk.stuba.fei.uim.oop.logic.Position;

@Getter
public class BoardCanvas extends Canvas {
    private static final int DEFAULT_BOARD_SIZE = 8;
    private BoardData boardData;
    @Setter
    private Position mousePos;
    private double tileSize;

    public BoardCanvas() {
        newBoard(DEFAULT_BOARD_SIZE);
        this.addMouseListener(new BoardListener());
        this.addMouseMotionListener(new BoardMotionListener());
    }

    public void newBoard(int size) {
        this.boardData = new BoardData(size);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        this.tileSize = ((double) this.getWidth()) / boardData.getSize();
        if (mousePos != null) {
            g.setColor(new Color(255, 0, 0));
            fillRect(g, mousePos.getX()*tileSize, mousePos.getY()*tileSize, tileSize, tileSize);
        }
        g.setColor(new Color(0, 0, 255));
        for (PipeData pipe : boardData.getRoute()) {
            drawPipe(g, pipe, tileSize);
        }
    }

    private void drawPipe(Graphics g, PipeData pipe, double tileSize) {
        double pxSize = tileSize/12;
        double tileX = tileSize*pipe.getPosition().getX();
        double tileY = tileSize*pipe.getPosition().getY();
        for (Orientation o : pipe.getPipeEnds()) {
            double rectX = tileX;
            double rectY = tileY;
            double rectWidth = 0.0;
            double rectHeight = 0.0;  
            switch (o) {
                case NORTH:
                    rectX += 4*pxSize;
                    rectY += 0;
                    rectWidth = 4*pxSize;
                    rectHeight = 8*pxSize;
                    break;
                case EAST:
                    rectX += 4*pxSize;
                    rectY += 4*pxSize;
                    rectWidth = 8*pxSize;
                    rectHeight = 4*pxSize;
                    break;
                case SOUTH:
                    rectX += 4*pxSize;
                    rectY += 4*pxSize;
                    rectWidth = 4*pxSize;
                    rectHeight = 8*pxSize;
                    break;
                case WEST:
                    rectX += 0*pxSize;
                    rectY += 4*pxSize;
                    rectWidth = 8*pxSize;
                    rectHeight = 4*pxSize;
                    break;
            }
            fillRect(g, rectX, rectY, rectWidth, rectHeight);
        }

    }

    private void fillRect(Graphics g, double x, double y, double width, double height) {
        g.fillRect((int) Math.round(x), (int) Math.round(y), (int) Math.round(width), (int) Math.round(height));
    }
}
