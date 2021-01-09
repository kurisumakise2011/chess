package funny.co.core;

import funny.co.ui.ChessboardBuilder;
import funny.co.ui.ChessboardPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;

public class GameController {
    private static final int DEFAULT_WIDTH = 512;
    private static final int DEFAULT_HEIGHT = 640;
    private double width;
    private double height;

    private StackPane chessboardHolder;
    private Runnable back;
    private Runnable forward;
    private Runnable maximize;
    private Runnable minimize;
    private Stage stage;

    public GameController(StackPane chessboardHolder, Stage stage) {
        this.chessboardHolder = chessboardHolder;
        this.stage = stage;
    }

    public void newGame() {
        if (!chessboardHolder.getChildren().isEmpty()) {
            chessboardHolder.getChildren().clear();
        }
        // creates new chessboard
        ChessboardPane chessboard = ChessboardBuilder.build(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        GameplayHandler gameplay = new GameplayHandler(chessboard);
        chessboardHolder.getChildren().addAll(chessboard);
        back = gameplay::back;
        forward = gameplay::forward;
        maximize = chessboard::maximize;
        minimize = chessboard::minimize;
        gameplay.bind();
    }

    public void back() {
        if (back != null) {
            back.run();
        }
    }

    public void forward() {
        if (forward != null) {
            forward.run();
        }
    }

    public void minimize() {
        if (minimize != null) {
            stage.setHeight(height);
            stage.setWidth(width);
            minimize.run();
        }
    }

    public void maximize() {
        if (maximize != null) {
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            stage.setWidth(size.getWidth());
            stage.setHeight(size.getHeight());
            maximize.run();
        }
    }

    public void screenCapture() {
        this.height = stage.getHeight() + 10;
        this.width = stage.getWidth() - 2;
    }
}
