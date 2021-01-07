package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.Chessboard;
import funny.co.model.Movable;
import funny.co.model.Position;

import java.util.List;

public interface PieceMovement {

    List<Position> allMoves(ChessSquare square, Chessboard chessboard);

    Movable canMove(ChessSquare square, Chessboard chessboard, Position position);

    void move(ChessSquare square, Chessboard chessboard, Position position);
}
