import com.google.gson.annotations.SerializedName;

public class StepJson {
    @SerializedName("_num")
    private final String num;
    @SerializedName("_playerId")
    private final String playerId;
    @SerializedName("__text")
    private final String text;

    public StepJson(Integer num, Integer playerId, Integer text) {
        this.num = String.valueOf(num);
        this.playerId = String.valueOf(playerId);
        this.text = String.valueOf(text);
    }
}
