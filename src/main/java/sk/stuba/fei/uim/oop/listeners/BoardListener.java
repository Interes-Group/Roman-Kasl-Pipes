package sk.stuba.fei.uim.oop.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sk.stuba.fei.uim.oop.gui.BoardCanvas;
import sk.stuba.fei.uim.oop.logic.Position;

public class BoardListener extends MouseAdapter {
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!(e.getComponent() instanceof BoardCanvas)) {
            return;
        }
        BoardCanvas boardCanvas = (BoardCanvas) e.getComponent();
        Position pos = new Position((int) (e.getX()/boardCanvas.getTileSize()), (int) (e.getY()/boardCanvas.getTileSize()));
        boardCanvas.getBoardData().rotatePipe(pos);
        boardCanvas.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!(e.getComponent() instanceof BoardCanvas)) {
            return;
        }
        BoardCanvas boardCanvas = (BoardCanvas) e.getComponent();
        Position mousePos = new Position((int) (e.getX()/boardCanvas.getTileSize()), (int) (e.getY()/boardCanvas.getTileSize()));
        if (mousePos.getX() >= boardCanvas.getBoardData().getSize()) {
            mousePos.setX(mousePos.getX()-1);
        }
        if (mousePos.getY() >= boardCanvas.getBoardData().getSize()) {
            mousePos.setY(mousePos.getY()-1);
        }
        boardCanvas.setMousePos(mousePos);
        boardCanvas.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!(e.getComponent() instanceof BoardCanvas)) {
            return;
        }
        BoardCanvas boardCanvas = (BoardCanvas) e.getComponent();
        boardCanvas.setMousePos(null);
        boardCanvas.repaint();
    }
}
