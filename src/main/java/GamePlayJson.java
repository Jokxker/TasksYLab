import com.google.gson.annotations.SerializedName;

public class GamePlayJson {
    @SerializedName("Gameplay")
    private final PlayerJson playerJson;

    public GamePlayJson(PlayerJson playerJson) {
        this.playerJson = playerJson;
    }
}
