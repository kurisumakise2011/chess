package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.Chessboard;
import funny.co.ui.ChessboardBuilder;
import funny.co.ui.ChessboardPane;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameplayHandler {
    private static final Logger log = Logger.getLogger(GameplayHandler.class.getName());
    private ChessboardPane pane;

    public GameplayHandler(ChessboardPane pane) {
        this.pane = pane;
    }

    public void bind() {
        Chessboard chessboard = new Chessboard(pane);
        chessboard.getSquares().forEach((position, square) -> {
            square.setOnMouseClicked(event -> {
                if (!square.isEnable()) {
                    return;
                }
                var piece = square.getPiece();
                if (piece != null && piece.isWhite() == chessboard.whiteMoves) {
                    click(square, chessboard);
                    return;
                }
                if (chessboard.selected != null) {
                    piece = chessboard.selected.getPiece();
                    var movement = piece.getMovement();
                    var mv = movement.canMove(chessboard.selected, chessboard, square.getPosition());
                    if (mv.isCanMove()) {
                        movement.move(chessboard.selected, chessboard, square.getPosition());
                        var pos = square.getPosition();
                        log.log(Level.INFO, "{0} {1}{2}", new Object[]{piece.getType().getName(), pos.getRank(), pos.getFile()});
                        square.setBackground(square.getFill());
                        chessboard.selected = null;
                        chessboard.whiteMoves = !chessboard.whiteMoves;
//                    } else if (mv.isCheck()) {
//                    } else if (mv.isMate()) {
                    } else {
                        pane.refresh();
                    }
                }
            });
        });
    }

    private void click(ChessSquare square, Chessboard chessboard) {
        pane.refresh();
        square.setBackground(ChessboardBuilder.selected);
        chessboard.selected = square;
    }
}
