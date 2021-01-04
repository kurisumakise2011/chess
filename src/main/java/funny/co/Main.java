package funny.co;

import funny.co.core.Caller;
import funny.co.core.GameplayHandler;
import funny.co.core.KeyboardHandler;
import funny.co.ui.Chessboard;
import funny.co.ui.ChessboardPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.logging.Logger;

public class Main extends Application implements Caller {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private Caller[] callers;

    public static void main(String[] args) {
        System.getProperties().list(System.out);
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        callers = initCallers();
        stage.setTitle("Chess");
        AnchorPane pane = new AnchorPane();

        ChessboardPane chessboard = Chessboard.build(800, 600);
        chessboard.setOnKeyReleased(new KeyboardHandler(this));

        GameplayHandler gameplay = new GameplayHandler(chessboard);
        gameplay.bind();
        pane.getChildren().add(chessboard);

        stage.setScene(new Scene(pane));
        stage.show();
    }

    private Caller[] initCallers() {
        return new Caller[]{};
    }

    @Override
    public void close() {
        Arrays.stream(callers).forEach(Caller::close);
        logger.info("all resources resolved, bye");
        System.exit(0);
    }
}
