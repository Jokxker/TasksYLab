import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JSONTicTacToe implements FileOfGameTicTacToe{
    @Override
    public void write(String outPath, PlayerTicTacToe playerX, PlayerTicTacToe player0, String winner) throws IOException {
        PlayerTicTacToe win = null;
        if (winner.equals(playerX.getName())) {
            win = playerX;
        } else if (winner.equals(player0.getName())) {
            win = player0;
        }
        GameJson gameJson = new GameJson();
        int j = 0;
        for (int i = 1; i <= playerX.getSteps().size() + player0.getSteps().size(); i++) {
            if (i % 2 != 0) {
                gameJson.setStep(new StepJson(i, 1, playerX.getSteps().get(j)));
            } else {
                gameJson.setStep(new StepJson(i, 2, player0.getSteps().get(j)));
                j++;
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        GamePlayJson gamePlayJson = new GamePlayJson(new PlayerJson(playerX, player0, gameJson, win));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outPath + ".json"));
        gson.toJson(gamePlayJson, writer);
        writer.flush();
        writer.close();
    }

    @Override
    public void read(String outPath) throws IOException {

    }
}
