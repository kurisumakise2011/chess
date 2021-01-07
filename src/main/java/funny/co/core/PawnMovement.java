package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PawnMovement extends AbstractPieceMovement {
    public List<Position> find(Map<Position, ChessSquare> squares, ChessSquare square) {
        List<Position> moves = new ArrayList<>(4);
        List<Position> possible = new ArrayList<>(2);
        var pos = square.getPosition();
        var piece = square.getPiece();
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
}
