import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GameJson {
    @SerializedName("Step")
    private final List<StepJson> stepJson = new ArrayList<>();

    public void setStep(StepJson steps) {
        stepJson.add(steps);
    }
}
