package sk.stuba.fei.uim.oop.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import sk.stuba.fei.uim.oop.gui.BoardCanvas;
import sk.stuba.fei.uim.oop.logic.Position;

public class BoardMotionListener extends MouseMotionAdapter {
    
    @Override
    public void mouseMoved(MouseEvent e) {
        if (!(e.getComponent() instanceof BoardCanvas)) {
            return;
        }
        BoardCanvas boardCanvas = (BoardCanvas) e.getComponent();
        Position newMousePos = new Position((int) (e.getX()/boardCanvas.getTileSize()), (int) (e.getY()/boardCanvas.getTileSize()));
        if (boardCanvas.getMousePos() == null || boardCanvas.getMousePos().equals(newMousePos)) {
            return;
        }
        boardCanvas.setMousePos(newMousePos);
        boardCanvas.repaint();
    }
}
