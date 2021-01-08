package funny.co.ui;

import funny.co.core.Caller;
import funny.co.core.GameController;
import funny.co.core.KeyboardHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InitializationUI {


    public static void init(Stage stage, Caller caller) {
        var image = new Image(InitializationUI.class.getResourceAsStream("/piece/min/black_queen.png"));
        stage.getIcons().addAll(image);

        stage.setTitle("Chess");
        AnchorPane pane = new AnchorPane();

        StackPane chessboardHolder = new StackPane();
        GameController controller = new GameController(chessboardHolder);
        chessboardHolder.setOnKeyReleased(new KeyboardHandler(caller));

        MenuPanel panel = new MenuPanel(controller);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(panel.getBar(), chessboardHolder);
        pane.getChildren().addAll(vBox);

        stage.setScene(new Scene(pane));

        controller.newGame();
        stage.show();
    }

}
