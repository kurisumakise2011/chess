package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.Position;
import funny.co.ui.ChessboardPane;
import funny.co.model.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class AbstractPieceMovement implements PieceMovement {
    public List<Position> allMoves(ChessSquare square, ChessboardPane chessboard) {
        Position position = square.getPosition();
        if (!position.validPosition()) {
            return Collections.emptyList();
        }
        return find(chessboard.getSquares(), square);
    }

    public abstract List<Position> find(Map<Position, ChessSquare> chessboard, ChessSquare piece);

    public boolean canMove(ChessSquare piece, ChessboardPane chessboard, Position position) {
        return allMoves(piece, chessboard).contains(position);
    }

    public void move(ChessSquare square, ChessboardPane chessboard, Position position) {
        if (canMove(square, chessboard, position)) {
            var piece = square.getPiece();
            // removes from previous square
            square.remove();

            Map<Position, ChessSquare> squares = chessboard.getSquares();
            ChessSquare target = squares.get(position);
            if (!target.isFree()) {
                chessboard.addRemoved(target.remove());
            }
            target.add(piece);
            chessboard.refresh();
        }
    }

    protected void append(List<Position> moves, Position position) {
        if (position.validPosition()) {
            moves.add(position);
        }
    }

    protected List<Position> filterFree(List<Position> possible, Map<Position, ChessSquare> squares) {
        return possible
                .stream()
                .map(squares::get)
                .filter(ChessSquare::isFree)
                .map(ChessSquare::getPosition)
                .collect(Collectors.toList());
    }

    protected List<Position> filterAttack(List<Position> possible, Map<Position, ChessSquare> squares, Piece piece) {
        return possible
                .stream()
                .map(squares::get)
                .filter(square -> square.isEnemyPiece(piece.isWhite()))
                .map(ChessSquare::getPosition)
                .collect(Collectors.toList());
    }

    protected List<Position> versatile(Map<Position, ChessSquare> squares, ChessSquare square, Position vec) {
        List<Position> possible = new ArrayList<>(8);
        var pos = square.getPosition();
        while (pos.validPosition()) {
            pos = pos.add(vec);
            var cs = squares.get(pos);
            if (cs == null || !cs.isFree()) {
                break;
            }
            append(possible, pos);
        }
        // add last and check if it's under attack
        ChessSquare cs = squares.get(pos);
        if (cs != null && cs.isEnemyPiece(square.getPiece().isWhite())) {
            append(possible, pos);
        }

        return possible;
    }
}
