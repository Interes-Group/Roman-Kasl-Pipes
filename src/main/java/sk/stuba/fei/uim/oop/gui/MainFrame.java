package sk.stuba.fei.uim.oop.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    BoardCanvas board = new BoardCanvas();
    MenuPanel menu = new MenuPanel();

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setSize(new Dimension(500, 500));
        setResizable(false);
        
        BoardCanvas board = new BoardCanvas();
        // board.setSize(600, 600);
        add(board);
        
        // MenuPanel menu = new MenuPanel();
        // menu.setSize(600, 100);
        // getContentPane().add(menu);

        // JPanel panel1 = new JPanel();
        // panel1.setBackground(Color.RED);
        // add(panel1);
        
        // JPanel panel2 = new JPanel();
        // panel2.setBackground(Color.BLUE);
        // add(panel2);

        setVisible(true);
        // panel1.setPreferredSize(new Dimension(getContentPane().getWidth(), getContentPane().getWidth()));
        // panel1.setMinimumSize(new Dimension(getContentPane().getWidth(), getContentPane().getWidth()));
    }
}
