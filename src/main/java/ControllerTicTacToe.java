import javax.xml.stream.XMLStreamException;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class ControllerTicTacToe {
    private final ModelTicTacToe model;
    private final ViewTicTacToe view;
    private int whoStep;
    private String winOrDraw;
    private final String path = "src/main/resources/";
    private int numFile = 0;
    private FileOfGameTicTacToe fileOfGameTicTacToe;

    public ControllerTicTacToe (ModelTicTacToe model, ViewTicTacToe view) {
        this.model = model;
        this.view = view;
    }

    public void startGame() {
        whoStep = 1;
        winOrDraw = " ";
        view.start();
        view.getButtonStart().addActionListener((e) -> {
            PlayerTicTacToe playerX = new PlayerTicTacToe(view.getTextName1(), 'X', "1");
            PlayerTicTacToe playerO = new PlayerTicTacToe(view.getTextName2(), 'O', "2");
            model.setGamer(playerX.getName(), playerO.getName());
            model.downloadGamers();
            view.gaming();
            Arrays.stream(view.getGameField()).toList().forEach((button) -> button.addActionListener((event) -> {
                if (whoStep % 2 != 0) {
                    button.setText("X");
                    button.setEnabled(false);
                    winOrDraw = model.changeField(event.getActionCommand(), playerX);
                } else {
                    button.setText("0");
                    button.setEnabled(false);
                    winOrDraw = model.changeField(event.getActionCommand(), playerO);
                }
                button.setFont(new Font("Helvetica", Font.PLAIN, 250));
                whoStep++;

                if (!winOrDraw.equals(" ")) {
                    String output = path + playerX.getName() + "-VS-" + playerO.getName() + ++numFile;
                    view.saveGame();
                    view.getButtonSave().addActionListener((xml) -> {
                        if (view.getTextSave().getText().toLowerCase().equals("xml")) {
                            fileOfGameTicTacToe = new XMLTicTacToe();
                        } else if (view.getTextSave().getText().toLowerCase().equals("json")) {
                            fileOfGameTicTacToe = new JSONTicTacToe();
                        }
                        try {
                            fileOfGameTicTacToe.write(output, playerX, playerO, winOrDraw);
                            fileOfGameTicTacToe.read(output);
                        } catch (XMLStreamException | IOException ex) {
                            ex.printStackTrace();
                        }
                        if (!winOrDraw.equals("Draw!")) model.setRating(winOrDraw);
                        model.writeGamers();
                        view.theEndWin(model.getGamers(), winOrDraw);
                        view.getButtonPlayAgain().addActionListener((playAgain) -> {
                            view.getFrame().dispose();
                            startGame();
                        });
                        view.getButtonNotPlay().addActionListener((notPlay) -> view.getFrame().dispose());
                    });
                }
            }));
        });
    }
}
