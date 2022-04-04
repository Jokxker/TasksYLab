import com.google.gson.Gson;
import spark.Spark;

import javax.xml.stream.XMLStreamException;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import static spark.Spark.*;

public class ControllerTicTacToe {
    // данные для запуска сервера
    private static final String JSON_MIME_TYPE = "application/json";
    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    private final ModelTicTacToe model;
    private final ViewTicTacToe view;
    private int whoStep;

    private String winOrDraw;
    private final String path = "src/main/resources/";
    private int numFile = 0;

    private FileOfGameTicTacToe fileOfGameTicTacToe;
    private PlayerTicTacToe playerX;
    private PlayerTicTacToe player0;

    public ControllerTicTacToe (ModelTicTacToe model, ViewTicTacToe view) {
        this.model = model;
        this.view = view;
    }

    // создание таблиц в базе данных
    private static void install() throws SQLException {

    }
    // удаление таблиц из базы данных
    private static void uninstall() throws SQLException {

    }
    // нормальная работа приложения
    private static void operate() throws SQLException {

    }

    public void startGameApi() {
        DatabaseH2 dbH2 = new DatabaseH2();
        dbH2.createTable();
        ipAddress(SERVER_HOST);
        port(SERVER_PORT);
        get("/gameplay", (request, response) -> { // Возвращаем рейтинг игроков
            model.downloadGamers();
           return new Gson().toJson(new StandardResponse(StatusResponse.OK, new Gson().toJsonTree(model.getGamers())));
        });
        post("/gameplay", (request, response) -> { // Добавляем игроков
            String nameX = request.queryParams("nameX");
            String name0 = request.queryParams("name0");
            if (nameX == null || name0 == null) {
                response.status(StatusResponse.BAD_REQUEST.getStatus());
            } else {
                playerX = new PlayerTicTacToe(nameX, 'X', "1");
                player0 = new PlayerTicTacToe(name0, '0', "2");
                dbH2.addPlayer(playerX.getName(), player0.getName());
                dbH2.show();
                model.setGamer(playerX.getName(), player0.getName());
                if (!model.getGamers().contains(playerX.getName()) || !model.getGamers().contains(player0.getName())) {
                    model.downloadGamers();
                    response.status(StatusResponse.INTERNAL_SERVER_ERROR.getStatus());
                }
            }
            return new Gson().toJson(response.status());
        });
        put("/gameplay", (request, response) -> { // Редактируем игровое поле
            winOrDraw = model.changeField(AdapterGameField.adapter(request.queryParams("step")), request.queryParams("name").equals(playerX.getName()) ? playerX : player0);
            if (winOrDraw.equals(" ")) {
                return new Gson().toJson(new StandardResponse(StatusResponse.OK));
            } else {
                if (!winOrDraw.equals("Draw!"))
                    dbH2.addPoint(winOrDraw);
                    model.setRating(winOrDraw);
                model.writeGamers();
                return new Gson().toJson(new StandardResponse(StatusResponse.OK, new Gson().toJsonTree(Gameplay.end(playerX, player0, winOrDraw))));
            }
        });
        delete("/gameplay", (request, response) -> { // Удаляем игрока по имени
            String name = request.queryParams("name");
            if (model.getGamers().contains(name)) {
                model.deleteGamer(name);
                if (!model.getGamers().contains(name)) {
                    model.writeGamers();
                    model.downloadGamers();
                    return new Gson().toJson(new StandardResponse(StatusResponse.OK));
                } else {
                    return new Gson().toJson(new StandardResponse(StatusResponse.INTERNAL_SERVER_ERROR));
                }
            }
            return new Gson().toJson(new StandardResponse(StatusResponse.BAD_REQUEST));
        });

        after((req, res) -> res.type(JSON_MIME_TYPE));
        Runtime.getRuntime().addShutdownHook(new Thread(Spark::stop));
    }

    public void startGame() {
        whoStep = 1;
        winOrDraw = " ";
        view.start();
        view.getButtonStart().addActionListener((e) -> { // Добавляем игроков
            playerX = new PlayerTicTacToe(view.getTextName1(), 'X', "1");
            player0 = new PlayerTicTacToe(view.getTextName2(), 'O', "2");
            model.setGamer(playerX.getName(), player0.getName());
            model.downloadGamers();
            view.gaming();
            Arrays.stream(view.getGameField()).toList().forEach((button) // Редактируем игровое поле
                    -> button.addActionListener((event) -> {
                if (whoStep % 2 != 0) {
                    button.setText("X");
                    button.setEnabled(false);
                    winOrDraw = model.changeField(event.getActionCommand(), playerX);
                } else {
                    button.setText("0");
                    button.setEnabled(false);
                    winOrDraw = model.changeField(event.getActionCommand(), player0);
                }
                button.setFont(new Font("Helvetica", Font.PLAIN, 250));
                whoStep++;

                if (!winOrDraw.equals(" ")) { // Выяняем исход игры и записываем ход игры в файл
                    String output = path + playerX.getName() + "-VS-" + player0.getName() + ++numFile;
                    view.saveGame();
                    view.getButtonSave().addActionListener((xml) -> {
                        if (view.getTextSave().getText().toLowerCase().equals("xml")) {
                            fileOfGameTicTacToe = new XMLTicTacToe();
                        } else if (view.getTextSave().getText().toLowerCase().equals("json")) {
                            fileOfGameTicTacToe = new JSONTicTacToe();
                        }
                        try {
                            fileOfGameTicTacToe.write(output, playerX, player0, winOrDraw);
                            fileOfGameTicTacToe.read(output);
                        } catch (XMLStreamException | IOException ex) {
                            ex.printStackTrace();
                        }
                        if (!winOrDraw.equals("Draw!")) model.setRating(winOrDraw);
                        model.writeGamers();
                        view.theEndWin(model.getGamers(), winOrDraw);
                        view.getButtonPlayAgain().addActionListener((playAgain) -> {
                            view.getFrame().dispose();
                            startGame();
                        });
                        view.getButtonNotPlay().addActionListener((notPlay) -> view.getFrame().dispose());
                    });
                }
            }));
        });
    }
}
