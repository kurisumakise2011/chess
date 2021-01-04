package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.ui.Chessboard;
import funny.co.ui.ChessboardPane;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameplayHandler {
    private static final Logger log = Logger.getLogger(GameplayHandler.class.getName());

    private ChessboardPane pane;
    private ChessSquare selected;
    private boolean whiteMoves = true;

    public GameplayHandler(ChessboardPane pane) {
        this.pane = pane;
    }

    public void bind() {
        pane.getSquares().forEach((position, square) -> {
            square.setOnMouseClicked(event -> {
                var piece = square.getPiece();
                if (piece != null && piece.isWhite() == whiteMoves) {
                    click(square);
                    return;
                }
                if (selected != null) {
                    piece = selected.getPiece();
                    var movement = piece.getMovement();
                    if (movement.canMove(selected, pane, square.getPosition())) {
                        movement.move(selected, pane, square.getPosition());
                        log.log(Level.INFO, "{0} {1}{2}", new Object[]{piece.getType().getName(), position.getRank(), position.getFile()});
                        square.setBackground(square.getFill());
                        selected = null;
                        whiteMoves = !whiteMoves;
                    } else {
                        pane.refresh();
                    }
                }
            });
        });
    }

    private void click(ChessSquare square) {
        pane.refresh();
        square.setBackground(Chessboard.selected);
        selected = square;
    }
}
