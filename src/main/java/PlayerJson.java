import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlayerJson {
    @SerializedName("Player")
    private final List<PlayerTicTacToe> players = new ArrayList<>();
    @SerializedName("Game")
    private GameJson gameJson;
    @SerializedName("GameResult")
    private GameResultJson gameResultJson;

    public PlayerJson(PlayerTicTacToe playerX, PlayerTicTacToe player0, GameJson gameJson, PlayerTicTacToe playerWin) {
        players.add(playerX);
        players.add(player0);
        this.gameJson = gameJson;
        this.gameResultJson = new GameResultJson(playerWin);
    }
}
