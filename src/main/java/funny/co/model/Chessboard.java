package funny.co.model;

import funny.co.ui.ChessboardPane;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Chessboard {
    private final Map<Position, ChessSquare> squares;
    private final List<Piece> removed = new LinkedList<>();
    private final Deque<Piece> moves = new LinkedList<>();
    private final ChessboardPane pane;
    public ChessSquare selected;
    public boolean whiteMoves = true;

    public Chessboard(ChessboardPane pane) {
        this.squares = pane.getSquares();
        this.pane = pane;
    }

    public Map<Position, ChessSquare> getSquares() {
        return squares;
    }

    public void addRemoved(Piece piece) {
        removed.add(piece);
    }

    public List<Piece> getRemoved() {
        return removed;
    }

    public ChessboardPane getPane() {
        return pane;
    }

    public void refresh() {
        this.pane.refresh();
    }

    public void disable() {
        pane.setOpacity(0.8);
        pane.disable();
    }

    public ChessSquare findKing(boolean white) {
        return squares.values()
                .stream()
                .filter(square -> square.getPiece() != null)
                .filter(square -> square.getPiece().getType() == PieceType.KING)
                .filter(square -> square.getPiece().isWhite() == white)
                .findAny()
                .get();
    }

    public List<ChessSquare> getPieces(boolean white) {
        return squares.values()
                .stream()
                .filter(square -> square.getPiece() != null)
                .filter(square -> square.getPiece().isWhite() == white)
                .collect(Collectors.toList());
    }

    public void enable() {
        pane.setOpacity(1.0);
        pane.enable();
    }

    public Deque<Piece> getMoves() {
        return moves;
    }
}
