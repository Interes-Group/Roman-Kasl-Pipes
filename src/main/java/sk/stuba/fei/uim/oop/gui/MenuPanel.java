package sk.stuba.fei.uim.oop.gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class MenuPanel extends JPanel {
    MainFrame mainFrame;
    JLabel scoreLabel;
    JSlider sizeModifier;
    JButton resetButton;
    JButton checkButton;

    public MenuPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        this.scoreLabel = new JLabel("Score: 0");
        this.add(this.scoreLabel);

        this.sizeModifier = new JSlider(JSlider.HORIZONTAL, 8, 12, 8);
        sizeModifier.setMinorTickSpacing(2);
        sizeModifier.setMajorTickSpacing(2);
        sizeModifier.setSnapToTicks(true);
        sizeModifier.setPaintTicks(true);
        sizeModifier.setPaintLabels(true);
        this.sizeModifier.addChangeListener(e-> {
            JSlider source = (JSlider) e.getSource();
            int value = source.getValue();
            mainFrame.changeBoardSize(value);
        });
        this.add(this.sizeModifier);

        this.resetButton = new JButton("Reset");
        this.resetButton.addActionListener(e -> {
            mainFrame.reset();
        });
        this.add(this.resetButton);

        checkButton = new JButton("Check");
        checkButton.addActionListener(e -> {
            mainFrame.checkGame();
        });
        add(checkButton);
    }

    public void showScore(int score) {
        scoreLabel.setText("Score: " + score);
    }
}
