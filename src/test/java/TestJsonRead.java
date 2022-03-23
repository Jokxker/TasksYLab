import java.io.IOException;

public class TestJsonRead {
    public static void main(String[] args) {
        try {
            new JSONTicTacToe().read("src/main/resources/loki-VS-tor1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
