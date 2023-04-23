package sk.stuba.fei.uim.oop.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class MenuPanel extends JPanel {
    private JLabel scoreLabel;
    private JLabel sizeLabel;
    private JSlider sizeModifier;
    private JButton resetButton;
    private JButton checkButton;

    public MenuPanel(MainFrame mainFrame) {
        checkButton = new JButton("Check");
        checkButton.addActionListener(e -> {
            mainFrame.checkGame();
        });
        add(checkButton);

        scoreLabel = new JLabel("Score: 0");
        add(this.scoreLabel);

        sizeModifier = new JSlider(JSlider.HORIZONTAL, 8, 12, 8);
        sizeModifier.setMinorTickSpacing(2);
        sizeModifier.setMajorTickSpacing(2);
        sizeModifier.setSnapToTicks(true);
        sizeModifier.setPaintTicks(true);
        sizeModifier.setPaintLabels(true);
        sizeModifier.addChangeListener(e-> {
            JSlider source = (JSlider) e.getSource();
            int value = source.getValue();
            mainFrame.changeBoardSize(value);
            sizeLabel.setText("Size: " + value + "x" + value);
        });
        sizeModifier.setPreferredSize(new Dimension(200, 50));
        add(this.sizeModifier);

        sizeLabel = new JLabel("Size: 8x8");
        add(this.sizeLabel);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            mainFrame.reset();
        });
        add(resetButton);
    }

    public void showScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}
