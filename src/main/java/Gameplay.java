import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Gameplay {
    @SerializedName("Player")
    private final List<PlayerTicTacToe> players = new ArrayList<>();
    @SerializedName("Steps")
    private final List<StepJson> steps;
    @SerializedName("GameResultWinner")
    private final PlayerTicTacToe winner;

    public Gameplay(PlayerTicTacToe playerX, PlayerTicTacToe player0, ArrayList<StepJson> steps, PlayerTicTacToe winner) {
        players.add(playerX);
        players.add(player0);
        this.steps = steps;
        this.winner = winner;
    }

    public static Gameplay end(PlayerTicTacToe playerX, PlayerTicTacToe player0, String winner) { // Создаем сущность - ход игры
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
        return new Gameplay(playerX, player0, steps, win);
    }

    public List<PlayerTicTacToe> getPlayers() {
        return players;
    }

    public List<StepJson> getSteps() {
        return steps;
    }

    public PlayerTicTacToe getWinner() {
        return winner;
    }
}
