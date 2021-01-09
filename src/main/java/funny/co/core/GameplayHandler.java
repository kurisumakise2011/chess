package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.ChessSquareState;
import funny.co.model.Chessboard;
import funny.co.model.Position;
import funny.co.ui.ChessboardBuilder;
import funny.co.ui.ChessboardPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameplayHandler {
    private static final Logger log = Logger.getLogger(GameplayHandler.class.getName());
    private ChessboardPane pane;
    private List<Map<Position, ChessSquareState>> history = new ArrayList<>();
    private int index = 0;
    private Chessboard chessboard;

    public GameplayHandler(ChessboardPane pane) {
        this.pane = pane;
    }

    public void bind() {
        chessboard = new Chessboard(pane);
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
                    if (movement.canMove(chessboard.selected, chessboard, square.getPosition())) {
                        // makes a copy
                        // under development
//                        var copy = chessboard.copy();
//                        history.add(copy);
//                        index++;

                        movement.move(chessboard.selected, chessboard, square.getPosition());
                        var pos = square.getPosition();
                        log.log(Level.INFO, "{0} {1}{2}", new Object[]{piece.getType().getName(), pos.getRank(), pos.getFile()});
                        chessboard.selected = null;
                        chessboard.whiteMoves = !chessboard.whiteMoves;
                        chessboard.getMoves().push(piece);
                    }
                    pane.refresh();
                }
            });
        });
    }

    private void click(ChessSquare square, Chessboard chessboard) {
        pane.refresh();
        square.setBackground(ChessboardBuilder.selected);
        chessboard.selected = square;

        var piece = square.getPiece();
        var mv = piece.getMovement();
        mv.allMoves(square, chessboard)
                .stream()
                .filter(pos -> mv.canMove(square, chessboard, pos))
                .forEach(pos -> {
                    ChessSquare sq = chessboard.getSquares().get(pos);
                    var fill = getPaint(sq.getBackground());
                    Background b;
                    if (sq.isFree()) {
                        b = new Background(fill, ChessboardBuilder.circle);
                    } else {
                        b = new Background(fill, ChessboardBuilder.corners);
                        sq.setOpacity(0.3);
                    }
                    sq.setBackground(b);
                });
    }

    private BackgroundFill getPaint(Background fill) {
        if (fill.getFills().size() > 0) {
            return fill.getFills().iterator().next();
        }
        return ChessboardBuilder.transparent;
    }

    public void back() {
        if (chessboard == null || history.isEmpty()) {
            return;
        }
        int i = index - 1;
        if (i == -1) {
            return;
        }
        var chessboard = history.get(i);
        this.chessboard.restore(chessboard);
        index--;
        this.chessboard.whiteMoves = !this.chessboard.whiteMoves;
    }

    public void forward() {
        if (chessboard == null || history.isEmpty()) {
            return;
        }
        int j = index - 1;
        if (j == history.size()) {
            return;
        }
        var chessboard = history.get(j);
        this.chessboard.restore(chessboard);
        index++;
        this.chessboard.whiteMoves = !this.chessboard.whiteMoves;
    }

}
