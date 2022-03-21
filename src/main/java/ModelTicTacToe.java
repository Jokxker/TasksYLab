import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class ModelTicTacToe {
    private final Map<String, Integer> gamers = new TreeMap<>();
    private final String pathRating = "src/main/resources/rating.txt";
    private char[][] fieldGame;

    public String getGamers() {
        StringBuilder gamersString = new StringBuilder();
        for (Map.Entry<String, Integer> entry : gamers.entrySet()) {
            gamersString.append(entry.getKey()).append(" - ").append(entry.getValue()).append(", ");
        }
        return String.valueOf(gamersString);
    }

    public void downloadGamers() {
        try(BufferedReader reader = new BufferedReader(new FileReader(pathRating))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String name = s.split(" - ")[0];
                Integer rating = Integer.parseInt(s.split(" - ")[1]);
                gamers.put(name, rating);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeGamers() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(pathRating))) {
            for (Map.Entry<String, Integer> entry : gamers.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue() + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setGamer(String name1, String name2) {
        gamers.put(name1, 0);
        gamers.put(name2, 0);
    }

    public void setRating(String nameRating) {
        gamers.put(nameRating, gamers.get(nameRating) + 1);
    }

    public String changeField(String step, PlayerTicTacToe player) {
        if (fieldGame == null) fieldGame = new char[3][3];
        int x = 0;
        int y = 0;
        switch (step) {
            case "2" -> x = 1;
            case "3" -> x = 2;
            case "4" -> y = 1;
            case "5" -> {
                y = 1;
                x = 1;
            }
            case "6" -> {
                y = 1;
                x = 2;
            }
            case "7" -> y = 2;
            case "8" -> {
                y = 2;
                x = 1;
            }
            case "9" -> {
                y = 2;
                x = 2;
            }
        }
        fieldGame[y][x] = player.getSymbol();
        player.setSteps(Integer.parseInt(step));
        int col = 0;
        int row = 0;
        int diag = 0;
        int rdiag = 0;
        for (int i = 0; i < 3; i++) {
            if (fieldGame[y][i] == player.getSymbol()) col++;
            if (fieldGame[i][x] == player.getSymbol()) row++;
            if (fieldGame[i][i] == player.getSymbol()) diag++;
            if (fieldGame[i][3 - (i + 1)] == player.getSymbol()) rdiag++;
        }
        if (row == 3 || col == 3 || diag == 3 || rdiag == 3) {
            fieldGame = null;
            return player.getName();
        }
        if (player.getSteps().size() > 4) {
            fieldGame = null;
            return "Draw!";
        }
        return " ";
    }
}
