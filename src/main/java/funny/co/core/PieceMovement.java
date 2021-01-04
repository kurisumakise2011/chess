package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.Position;
import funny.co.ui.ChessboardPane;

import java.util.List;

public interface PieceMovement {

    List<Position> allMoves(ChessSquare square, ChessboardPane chessboard);

    boolean canMove(ChessSquare square, ChessboardPane chessboard, Position position);

    void move(ChessSquare square, ChessboardPane chessboard, Position position);
}
