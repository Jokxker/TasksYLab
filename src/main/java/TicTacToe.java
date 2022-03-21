public class TicTacToe {
    public static void main(String[] args) {
        ModelTicTacToe model = new ModelTicTacToe();
        ViewTicTacToe view = new ViewTicTacToe();
        ControllerTicTacToe controller = new ControllerTicTacToe(model, view);
        controller.startGame();
    }
}
