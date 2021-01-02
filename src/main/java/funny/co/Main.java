package funny.co;

import funny.co.core.Caller;
import funny.co.core.KeyboardHandler;
import funny.co.ui.Chessboard;
import javafx.application.Application;
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
        Chessboard chessboard = Chessboard.build(width(), height());
        chessboard.setOnKeyReleased(new KeyboardHandler(this));

        stage.setScene(chessboard);
        stage.show();
    }

    private double width() {
        return 800;
    }

    private double height() {
        return 600;
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
