package sk.stuba.fei.uim.oop.gui;

import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private BoardCanvas board;
    private MenuPanel menu; 
    private int score = 0;

    public MainFrame() {
        setSize(700, 780);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        board = new BoardCanvas();
        add(board);
        menu = new MenuPanel(this);
        add(menu, BorderLayout.SOUTH);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_R:
                        reset();
                        break;
                        
                    case KeyEvent.VK_ENTER:
                        checkGame();
                        break;
                    
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                
                    default:
                }
            }
            return false;
        }); 

        setVisible(true);
    }

    public void reset() {
        board.newBoard();
        this.score = 0;
        menu.showScore(this.score);
    }

    public void checkGame() {
        if (board.getBoardData().isRouteAlligned()) {
            board.newBoard();
            menu.showScore(++this.score);
        }
        else {
            board.showCorrect();
        }
    }

    public void changeBoardSize(int newSize) {
        board.newBoard(newSize);
        this.score = 0;
        menu.showScore(this.score);
    }
}
