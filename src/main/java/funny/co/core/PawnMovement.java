package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.Chessboard;
import funny.co.model.PieceType;
import funny.co.model.Position;
import funny.co.ui.ChessboardBuilder;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PawnMovement extends AbstractPieceMovement {
    public List<Position> find(Chessboard chessboard, ChessSquare square) {
        var squares = chessboard.getSquares();
        List<Position> moves = new ArrayList<>(4);
        List<Position> possible = new ArrayList<>(2);
        var pos = square.getPosition();
        var piece = square.getPiece();
        if (pos.getRow() == 7 && piece.isWhite()) {
            pawnPromotion(chessboard, square);
        }
        if (pos.getRow() == 0 && !piece.isWhite()) {
            pawnPromotion(chessboard, square);
        }
        if (pos.getRow() == 1 || pos.getRow() == 6) {
            append(possible, pos.add(Position.of(2, 0).mult(piece.getDirection())));
        }
        append(possible, pos.add(Position.of(1, 0).mult(piece.getDirection())));

        moves.addAll(filterFree(possible, squares));
        possible.clear();

        append(possible, pos.add(Position.of(1, -1).mult(piece.getDirection())));
        append(possible, pos.add(Position.of(1, 1).mult(piece.getDirection())));

        moves.addAll(filterAttack(possible, squares, piece.isWhite()));

        return moves;
    }

    private void pawnPromotion(Chessboard chessboard, ChessSquare square) {
        chessboard.disable();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Pawn promotion");
        alert.setHeaderText("Choose piece type");

        ButtonType queen = new ButtonType("Queen");
        ButtonType rock = new ButtonType("Rock");
        ButtonType bishop = new ButtonType("Bishop");
        ButtonType knight = new ButtonType("Knight");

        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(queen, rock, bishop, knight);

        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent()) {
            ButtonType type = option.get();
            PieceType pieceType = null;
            if (type == queen) {
                pieceType = PieceType.QUEEN;
            }
            if (type == rock) {
                pieceType = PieceType.ROCK;
            }
            if (type == bishop) {
                pieceType = PieceType.BISHOP;
            }
            if (type == knight) {
                pieceType = PieceType.KNIGHT;
            }
            boolean colour = square.getPiece().isWhite();
            var piece = ChessboardBuilder.createPiece(pieceType, colour);

            square.remove();
            square.add(piece);
        }

        chessboard.enable();
    }
}
