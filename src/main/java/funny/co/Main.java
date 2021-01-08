package funny.co;

import funny.co.core.Caller;
import funny.co.core.GameController;
import funny.co.core.GameplayHandler;
import funny.co.core.KeyboardHandler;
import funny.co.ui.ChessboardBuilder;
import funny.co.ui.ChessboardPane;
import funny.co.ui.InitializationUI;
import funny.co.ui.MenuPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    public void start(Stage stage) {
        callers = initCallers();
        InitializationUI.init(stage, this);
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
