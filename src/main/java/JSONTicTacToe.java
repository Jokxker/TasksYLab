import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class JSONTicTacToe implements FileOfGameTicTacToe{ // Записываем и читаем - ход игры в json формате
    @Override
    public void write(String outPath, PlayerTicTacToe playerX, PlayerTicTacToe player0, String winner) throws IOException {
        PlayerTicTacToe win = new PlayerTicTacToe("Draw!", null, null);
        if (winner.equals(playerX.getName())) {
            win = playerX;
        } else if (winner.equals(player0.getName())) {
            win = player0;
        }
        ArrayList<StepJson> steps = new ArrayList<>();
        int j = 0;
        for (int i = 1; i <= playerX.getSteps().size() + player0.getSteps().size(); i++) {
            if (i % 2 != 0) {
                steps.add(new StepJson(i, 1, playerX.getSteps().get(j)));
            } else {
                steps.add(new StepJson(i, 2, player0.getSteps().get(j)));
                j++;
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Gameplay json = new Gameplay(playerX, player0, steps, win);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outPath + ".json"));
        gson.toJson(json, writer);
        writer.flush();
        writer.close();
    }

    @Override
    public void read(String outPath) throws IOException {
        char[] fieldGame = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char step = ' ';
        Gson gson = new Gson();
        Reader reader = new FileReader(outPath + ".json");
        Gameplay json = gson.fromJson(reader, Gameplay.class);
        for (StepJson s : json.getSteps()) {
            if (s.getPlayerId().equals("1")) step = 'X';
            if (s.getPlayerId().equals("2")) step = '0';
            switch (s.getText()) {
                case "1" -> fieldGame[0] = step;
                case "2" -> fieldGame[1] = step;
                case "3" -> fieldGame[2] = step;
                case "4" -> fieldGame[3] = step;
                case "5" -> fieldGame[4] = step;
                case "6" -> fieldGame[5] = step;
                case "7" -> fieldGame[6] = step;
                case "8" -> fieldGame[7] = step;
                case "9" -> fieldGame[8] = step;
            }
            for (int i = 0; i < 9; i += 3) {
                System.out.println("|" + fieldGame[i] + "|" + fieldGame[i + 1] + "|" + fieldGame[i + 2] + "|");
            }
            System.out.println();
        }
        if (json.getWinner().getSymbol() == null) {
            System.out.println("Draw!");
        } else {
            System.out.println("Player " + json.getWinner().getID() + " -> " + json.getWinner().getName() + " is winner as '" + json.getWinner().getSymbol() + "'!");
        }
    }
}
