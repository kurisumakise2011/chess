package funny.co.core;

import funny.co.model.ChessSquare;
import funny.co.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KingMovement extends AbstractPieceMovement {
    private final List<Position> dirs = List.of(
            Position.of(-1, 0),
            Position.of(0, 1),
            Position.of(1, 0),
            Position.of(0, -1),
            Position.of(1, 1),
            Position.of(-1, 1),
            Position.of(-1, -1),
            Position.of(1, -1)
    );

    @Override
    public List<Position> find(Map<Position, ChessSquare> squares, ChessSquare square) {
        List<Position> possible = new ArrayList<>(8);
        var current = square.getPosition();
        for (Position dir : dirs) {
            var pos = current.add(dir);
            if (!pos.validPosition()) {
                continue;
            }
            var cs = squares.get(pos);
            if (cs.isFree()) {
                append(possible, pos);
            } else {
                if (cs.isEnemyPiece(square.getPiece().isWhite())) {
                    append(possible, pos);
                }
            }
        }

        return possible;
    }
}
