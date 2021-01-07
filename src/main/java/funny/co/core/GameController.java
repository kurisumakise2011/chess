package funny.co.core;

import funny.co.ui.ChessboardPane;
import funny.co.ui.MenuPane;

public class GameController {
    private ChessboardPane chessboardPane;
    private MenuPane menuPane;

    public GameController(ChessboardPane chessboardPane, MenuPane menuPane) {
        this.chessboardPane = chessboardPane;
        this.menuPane = menuPane;
    }
}
