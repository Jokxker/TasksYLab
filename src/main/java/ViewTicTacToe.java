import javax.swing.*;
import java.awt.*;

public class ViewTicTacToe {
    private JFrame frame;
    private JButton[] gameField;
    private JPanel panel;
    private JButton buttonStart;
    private JButton buttonPlayAgain;
    private JButton buttonNotPlay;
    private JButton buttonXml;
    private JButton buttonJson;
    private JTextField textName1;
    private JTextField textName2;

    public JFrame getFrame() {
        return frame;
    }

    public String getTextName1() {
        return textName1.getText();
    }

    public String getTextName2() {
        return textName2.getText();
    }

    public JButton[] getGameField() {
        return gameField;
    }

    public JButton getButtonStart() {
        return buttonStart;
    }

    public JButton getButtonPlayAgain() {
        return buttonPlayAgain;
    }

    public JButton getButtonNotPlay() {
        return buttonNotPlay;
    }

    public JButton getButtonXml() {
        return buttonXml;
    }

    public JButton getButtonJson() {
        return buttonJson;
    }

    public void start() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setSize(800, 800);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 800));
        JLabel labelName1 = new JLabel("Cross enter name: ");
        JLabel labelName2 = new JLabel("Zero enter name: ");
        textName1 = new JTextField(15);
        textName2 = new JTextField(15);
        buttonStart = new JButton("Start");
        JLabel labelRules = new JLabel("You'll receive 1 point->winning and 0->defeat or draw");
        labelRules.setFont(new Font("TimesRoman", Font.BOLD, 25));
        panel.add(labelName1);
        panel.add(textName1);
        panel.add(labelName2);
        panel.add(textName2);
        panel.add(buttonStart);
        panel.add(labelRules);
        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void gaming() {
        frame.getContentPane().removeAll();
        gameField = new JButton[9];
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 800));
        for(int i = 0; i < gameField.length; i++) {
            gameField[i] = new JButton(String.valueOf(i + 1));
            gameField[i].setForeground(Color.LIGHT_GRAY);
            gameField[i].setBackground(Color.LIGHT_GRAY);
            gameField[i].setPreferredSize(new Dimension(250, 240));
            panel.add(gameField[i]);
        }
        frame.getContentPane().add(panel);
        frame.getContentPane().revalidate();
    }

    public void saveGame() {
        frame.getContentPane().removeAll();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 800));
        JLabel labelSave = new JLabel("How to save the game? ");
        buttonXml = new JButton("XML");
        buttonJson = new JButton("JSON");
        panel.add(labelSave);
        panel.add(buttonXml);
        panel.add(buttonJson);
        frame.getContentPane().add(panel);
        frame.getContentPane().revalidate();
    }

    public void theEndWin(String rating, String nameWin) {
        frame.getContentPane().removeAll();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(800, 800));
        JLabel labelRating = new JLabel( "Winner - " + nameWin + "! Overall rating " + rating);
        JLabel labelPlayAgain = new JLabel("Do you want to play again?");
        buttonPlayAgain = new JButton("Yes of course)");
        buttonNotPlay = new JButton("No, I'm tired");
        panel.add(labelRating);
        panel.add(labelPlayAgain);
        panel.add(buttonPlayAgain);
        panel.add(buttonNotPlay);
        frame.getContentPane().add(panel);
        frame.getContentPane().revalidate();
    }
}
