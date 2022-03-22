import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GameJson {
    @SerializedName("Player")
    private final List<PlayerTicTacToe> players = new ArrayList<>();
    @SerializedName("Steps")
    private final List<StepJson> steps;
    @SerializedName("GameResultWinner")
    private final PlayerTicTacToe winner;

    public GameJson(PlayerTicTacToe playerX, PlayerTicTacToe player0, ArrayList<StepJson> steps, PlayerTicTacToe winner) {
        players.add(playerX);
        players.add(player0);
        this.steps = steps;
        this.winner = winner;
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
