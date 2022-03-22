import java.io.IOException;

public class TestJSONTicTacToeWrite {
    public static void main(String[] args) {
        PlayerTicTacToe playerX = new PlayerTicTacToe("loki", 'X', "1");
        PlayerTicTacToe player0 = new PlayerTicTacToe("tor", '0', "2");
        playerX.setSteps(1);
        player0.setSteps(5);
        playerX.setSteps(2);
        player0.setSteps(3);
        playerX.setSteps(4);
        player0.setSteps(7);
        try {
            new JSONTicTacToe().write("src/test/resources/test", playerX, player0, "tor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
