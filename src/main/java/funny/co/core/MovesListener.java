package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.Move;
import funny.co.model.PieceType;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MovesListener {
    private List<Move> moves = new LinkedList<>();
    private boolean blackKingMoved = false;
    private boolean whiteKingMoved = false;

    public void onMove(@NotNull ChessSquare from, @NotNull ChessSquare to) {
        var piece = from.getPiece();
        Objects.requireNonNull(piece);
        var pos = to.getPosition();

        var move = Move.builder()
                .from(from.getPosition())
                .to(to.getPosition())
                .piece(piece.getType())
                .white(piece.isWhite())
                .notation(String.format("%s%s [%s %s]", pos.getRank(), pos.getFile(), piece.getType(), piece.isWhite() ? "white" : "black"))
                .build();
        if (move.getPiece() == PieceType.KING) {
            if (move.isWhite()) {
                whiteKingMoved = true;
            } else {
                blackKingMoved = true;
            }
        }

        moves.add(move);
    }

    public boolean canCastling(boolean whiteCastling) {
        return (whiteCastling && !whiteKingMoved) || (!whiteCastling && !blackKingMoved);
    }

    public List<Move> getMoves() {
        return moves;
    }
}
