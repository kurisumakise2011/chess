package funny.co.core;

import funny.co.ui.ChessboardBuilder;
import funny.co.ui.ChessboardPane;
import javafx.scene.layout.StackPane;

public class GameController {
    private StackPane chessboardHolder;

    public GameController(StackPane chessboardHolder) {
        this.chessboardHolder = chessboardHolder;
    }

    public void newGame() {
        if (!chessboardHolder.getChildren().isEmpty()) {
            chessboardHolder.getChildren().clear();
        }
        // creates new chessboard
        ChessboardPane chessboard = ChessboardBuilder.build(800 ,600);
        GameplayHandler gameplay = new GameplayHandler(chessboard);
        chessboardHolder.getChildren().addAll(chessboard);

        gameplay.bind();
    }
}
