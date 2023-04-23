package sk.stuba.fei.uim.oop.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
    private boolean showCorrect = false;
    private ArrayList<Position> correctPipes;

    public BoardCanvas() {
        newBoard(DEFAULT_BOARD_SIZE);
        this.addMouseListener(new BoardListener());
        this.addMouseMotionListener(new BoardMotionListener());
    }

    public void newBoard(int size) {
        this.boardData = new BoardData(size);
        repaint();
    }

    public void newBoard() {
        this.boardData = new BoardData(boardData.getSize());
        repaint();
    }

    public void showCorrect() {
        this.correctPipes = this.boardData.getCorrectPipes();
        repaint();
    }

    public void hideCorrect() {
        this.correctPipes = null;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        this.tileSize = ((double) this.getWidth()) / boardData.getSize();
        Position start = boardData.getRoute().get(0).getPosition();
        Position finish = boardData.getRoute().get(boardData.getRoute().size()-1).getPosition();
        g.setColor(new Color(144, 176, 255));
        fillRect(g, start.getX()*tileSize, start.getY()*tileSize, tileSize, tileSize);
        fillRect(g, finish.getX()*tileSize, finish.getY()*tileSize, tileSize, tileSize);
        if (mousePos != null) {
            g.setColor(new Color(250, 221, 129));
            fillRect(g, mousePos.getX()*tileSize, mousePos.getY()*tileSize, tileSize, tileSize);
        }
        g.setColor(new Color(88, 125, 255));
        for (PipeData pipe : boardData.getRoute()) {
            if (this.correctPipes != null) {
                if (this.correctPipes.contains(pipe.getPosition())) {
                    g.setColor(new Color(51, 193, 91));
                    drawPipe(g, pipe, tileSize);
                    g.setColor(new Color(88, 125, 255));
                    continue;
                }
            }
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
