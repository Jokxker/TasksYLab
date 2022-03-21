import com.google.gson.annotations.SerializedName;

public class GameResultJson {
    @SerializedName("Player")
    private final PlayerTicTacToe player;

    public GameResultJson(PlayerTicTacToe player) {
        this.player = player;
    }
}
