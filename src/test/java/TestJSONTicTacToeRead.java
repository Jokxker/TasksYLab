import java.io.IOException;

public class TestJSONTicTacToeRead {
    public static void main(String[] args) {
        try {
            new JSONTicTacToe().read("src/test/resources/test.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
